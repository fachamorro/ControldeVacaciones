package gob.distrito05d03salud.controlvacaciones.fachada;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gob.distrito05d03salud.controlvacaciones.dominio.administracion.Departamento;
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.Expediente;
import gob.distrito05d03salud.controlvacaciones.dominio.sujeto.Funcionario;
import gob.distrito05d03salud.controlvacaciones.servicio.DepartamentoService;
import gob.distrito05d03salud.controlvacaciones.servicio.ExpedienteService;
import gob.distrito05d03salud.controlvacaciones.util.FormatoFechas;

@Component
public class AdministracionFachada {

    @Autowired
    private DepartamentoService departamentoServicio;
    @Autowired
    private FormatoFechas formatoFechas;
    // ****** DEPARTAMENTO **************/

    public Departamento nuevoDepartamento() {
        Departamento departamento = new Departamento();
        return departamento;
    }

    public Departamento guardarDepartamento(Departamento departamento) {
        return departamentoServicio.guardar(departamento);
    }

    public Departamento buscarDepartamentoId(long id) {
        return departamentoServicio.buscarPorId(id);

    }

    public List<Departamento> buscarListaTodosDepartamento() {
        return departamentoServicio.listarTodosDepartamento();
    }

    public Departamento actualizarDepartamento(Departamento departamento) {
        return departamentoServicio.guardar(departamento);
    }

    public void eliminarDepartamento(long id) {
        departamentoServicio.eliminar(id);
    }

    public void inactivarDepartamento(long id) {
        Departamento dpto = buscarDepartamentoId(id);
        dpto.setEstado(false);
        guardarDepartamento(dpto);
    }

    @Autowired
    private ExpedienteService expedienteServicio;

    // ****** EXPEDIENTE **************/

    public Expediente nuevoExpediente() {
        return new Expediente();
    }

    public Expediente nuevoExpediente(Funcionario funcionario) {
        return new Expediente(funcionario);
    }

    public Expediente nuevoExpedienteFechaIngresoFuncionario(Funcionario funcionario) throws ParseException {
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        fechaInicio.setTime(funcionario.getFechaIngreso());
        fechaFin.setTime(
                formatoFechas.retornarFinAnioFechaFormatoCalendario(funcionario.getFechaIngreso()));
        Expediente expediente = new Expediente(fechaInicio.get(Calendar.YEAR),
                funcionario, fechaInicio.getTime(), fechaFin.getTime(),
                0, 0, 0);
        guardarExpediente(expediente);
        return expediente;
    }

    public Expediente nuevoExpediente(int anio, Funcionario funcionario, Date fechaInicioExpediente,
            Date fechaFinExpediente, Integer devengadoActualDias, Integer devengadoActualHoras,
            Integer devengadoActualMinutos) {
        Expediente expediente = new Expediente(anio, funcionario, fechaInicioExpediente, fechaFinExpediente,
                devengadoActualDias, devengadoActualHoras, devengadoActualMinutos);
        return expediente;
    }

    public Expediente guardarExpediente(Expediente expediente) {
        return expedienteServicio.guardar(validarHorasMinutosExpediente(expediente));
    }

    public Expediente validarHorasMinutosExpediente(Expediente expediente) {
        if (expediente.getDevengadoActualMinutos() > 59) {
            expediente.setDevengadoActualHoras(expediente.getDevengadoActualHoras() + 1);
            expediente.setDevengadoActualMinutos(expediente.getDevengadoActualMinutos() - 60);
        }
        if (expediente.getDevengadoActualHoras() > 7) {
            expediente.setDevengadoActualDias(expediente.getDevengadoActualDias() + 1);
            expediente.setDevengadoActualHoras(expediente.getDevengadoActualHoras() - 8);
        }

        if (expediente.getDevengadoActualMinutos() < 0) {
            expediente.setDevengadoActualHoras(expediente.getDevengadoActualHoras() - 1);
            expediente.setDevengadoActualMinutos(60 + expediente.getDevengadoActualMinutos());
        }
        if (expediente.getDevengadoActualHoras() < 0) {
            expediente.setDevengadoActualDias(expediente.getDevengadoActualDias() - 1);
            expediente.setDevengadoActualHoras(8 + expediente.getDevengadoActualHoras());
        }

        return expediente;
    }

    public Expediente buscarExpedienteId(long id) {
        return expedienteServicio.buscarPorId(id);

    }

    public List<Expediente> buscarListaExpedienteFuncionario(Funcionario funcionario) {
        return expedienteServicio.buscarListaExpedienteFuncionario(funcionario);

    }
    public List<Expediente> buscarListaExpedienteAnio(int anio) {
        return expedienteServicio.buscarListaExpedientesAnio(anio);

    }

    public Expediente buscarExpedienteFuncionarioAnio(Funcionario funcionario, Integer anioExpediente) {
        return expedienteServicio.buscarExpedienteFuncionarioAnio(funcionario, anioExpediente);

    }

    public List<Expediente> buscarListaTodosExpediente() {
        return expedienteServicio.listarTodosExpedientes();
    }

    public List<Expediente> buscarListaExpedientesDepartamento(List<Funcionario> listaFuncionarios) {
        List<Expediente> listaExpedientesDepartamento = new ArrayList<>();
        for (Funcionario funcionario : listaFuncionarios) {
            List<Expediente> listaExpedientes = buscarListaExpedienteFuncionario(funcionario);
            for (Expediente expediente : listaExpedientes) {
                listaExpedientesDepartamento.add(expediente);
            }
        }
        return listaExpedientesDepartamento;
    }

    public Expediente actualizarExpediente(Expediente expediente) {
        return expedienteServicio.guardar(validarHorasMinutosExpediente(expediente));
    }

    public void eliminarExpediente(long id) {
        expedienteServicio.eliminar(id);
    }

}
