package gob.distrito05d03salud.controlvacaciones.fachada;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.Expediente;
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.PermisoSalida;
import gob.distrito05d03salud.controlvacaciones.dominio.controlvacaciones.SolicitudVacaciones;
import gob.distrito05d03salud.controlvacaciones.servicio.PermisoSalidaService;
import gob.distrito05d03salud.controlvacaciones.servicio.SolicitudVacacionesService;
import gob.distrito05d03salud.controlvacaciones.util.FormatoFechas;

@Component
public class ControlVacacionesFachada {

    @Autowired
    private PermisoSalidaService permisoSalidaServicio;
    @Autowired
    private SolicitudVacacionesService solicitudVacacionesServicio;
    @Autowired
    private AdministracionFachada administracionFachada;
    @Autowired
    private FormatoFechas formatoFechas;

    // ****** PERMISOS DE SALIDA **************/

    public PermisoSalida nuevoPermisoSalida() {
        PermisoSalida permisoSalida = new PermisoSalida();
        return permisoSalida;
    }

    public boolean guardarPermisoSalida(PermisoSalida permisoSalida) throws ParseException {
        // validar nuevo o editar Permiso Salida
        if (permisoSalida.getId() == null) {
            // Fijar fechas de expediente
            Calendar fechaInicio = Calendar.getInstance();
            Calendar fechaFin = Calendar.getInstance();
            fechaInicio.setTime(
                    formatoFechas.retornarInicioAnioFechaFormatoCalendario(permisoSalida.getFechaInicioPermiso()));
            fechaFin.setTime(
                    formatoFechas.retornarFinAnioFechaFormatoCalendario(permisoSalida.getFechaInicioPermiso()));
            // buscar expediente
            Expediente expediente = administracionFachada.buscarExpedienteFuncionarioAnio(
                    permisoSalida.getExpediente().getFuncionario(),
                    fechaInicio.get(Calendar.YEAR));
            // crear expediente en caso de no existencia
            if (expediente == null) {
                expediente = administracionFachada.nuevoExpediente(fechaInicio.get(Calendar.YEAR),
                        permisoSalida.getExpediente().getFuncionario(), fechaInicio.getTime(), fechaFin.getTime(),
                        0, 0, 0);
                administracionFachada.guardarExpediente(expediente);
            }
            permisoSalida.setExpediente(expediente);
            // actualiza expediente
            if (permisoSalidaServicio.guardar(permisoSalida).isCargoVacaciones() == true) {
                expediente.setDevengadoActualDias(expediente.getDevengadoActualDias() + permisoSalida.getNumeroDias());
                expediente
                        .setDevengadoActualHoras(expediente.getDevengadoActualHoras() + permisoSalida.getNumeroHoras());
                expediente.setDevengadoActualMinutos(
                        expediente.getDevengadoActualMinutos() + permisoSalida.getNumeroMinutos());
                administracionFachada.actualizarExpediente(expediente);
            }
            return true;
        } else {
            // Eliminar permiso existente y crear nuevo
            eliminarPermisoSalida(permisoSalida.getId());
            permisoSalida.setId(null);
            guardarPermisoSalida(permisoSalida);
            return true;
        }
    }

    public PermisoSalida buscarPermisoSalidaId(long id) {
        return permisoSalidaServicio.buscarPorId(id);

    }

    public long contarPermisosSalidaExpediente(Expediente expediente) {
        return permisoSalidaServicio.contarPermisosExpediente(expediente);

    }

    public List<PermisoSalida> buscarListaPermisosSalidaFuncionario(long id, Date fechaInicio, Date fechaFin) {
        // Falta Buscar expediente del funcionario para obtener id expediente
        return permisoSalidaServicio.listarPermisosExpedienteFechainicioFechafin(0, fechaInicio, fechaFin);
    }

    public List<PermisoSalida> buscarListaPermisosSalidaExpediente(Expediente expediente) {
        // Falta Buscar expediente del funcionario para obtener id expediente
        return permisoSalidaServicio.listarPermisosExpediente(expediente);
    }

    public List<PermisoSalida> buscarListaTodosPermisosSalida() {
        return permisoSalidaServicio.listarTodosPermisos();
    }

    public PermisoSalida actualizarPermisoSalida(PermisoSalida permisoSalida) {
        return permisoSalidaServicio.guardar(permisoSalida);
    }

    public void eliminarPermisoSalida(long id) {
        PermisoSalida permisoSalida = this.buscarPermisoSalidaId(id);
        if (permisoSalida.isCargoVacaciones()) {
            Expediente expediente = permisoSalida.getExpediente();
            expediente.setDevengadoActualDias(expediente.getDevengadoActualDias() - permisoSalida.getNumeroDias());
            expediente.setDevengadoActualHoras(expediente.getDevengadoActualHoras() - permisoSalida.getNumeroHoras());
            expediente.setDevengadoActualMinutos(
                    expediente.getDevengadoActualMinutos() - permisoSalida.getNumeroMinutos());
            administracionFachada.guardarExpediente(expediente);
        }
        permisoSalidaServicio.eliminar(id);
    }

    // ****** SOLICITUD DE VACACIONES **************/

    public SolicitudVacaciones nuevoSolicitudVacaciones() {
        SolicitudVacaciones solicitudVacaciones = new SolicitudVacaciones();
        return solicitudVacaciones;
    }

    public boolean guardarSolicitudVacaciones(SolicitudVacaciones solicitudVacaciones) throws ParseException {
        // validar nuevo o editar Permiso Salida
        if (solicitudVacaciones.getId() == null) {
            // Fijar fechas de expediente
            Calendar fechaInicio = Calendar.getInstance();
            Calendar fechaFin = Calendar.getInstance();
            fechaInicio.setTime(formatoFechas
                    .retornarInicioAnioFechaFormatoCalendario(solicitudVacaciones.getFechaInicioVacaciones()));
            fechaFin.setTime(formatoFechas
                    .retornarFinAnioFechaFormatoCalendario(solicitudVacaciones.getFechaInicioVacaciones()));
            // buscar expediente
            Expediente expediente = administracionFachada.buscarExpedienteFuncionarioAnio(
                    solicitudVacaciones.getExpediente().getFuncionario(),
                    fechaInicio.get(Calendar.YEAR));

            // crear expediente en caso de no existencia
            if (expediente == null) {
                expediente = administracionFachada.nuevoExpediente(fechaInicio.get(Calendar.YEAR),
                        solicitudVacaciones.getExpediente().getFuncionario(), fechaInicio.getTime(), fechaFin.getTime(),
                        0, 0, 0);
                administracionFachada.guardarExpediente(expediente);

            }
            solicitudVacaciones.setExpediente(expediente);
            // actualiza expediente
            if (solicitudVacacionesServicio.guardar(solicitudVacaciones) != null) {
                expediente.setDevengadoActualDias(
                        expediente.getDevengadoActualDias() + solicitudVacaciones.getNumeroDias());
                administracionFachada.actualizarExpediente(expediente);
                return true;
            } else {
                return false;
            }
        } else {
            SolicitudVacaciones solicitudVacacionesAnterior = this
                    .buscarSolicitudVacacionesId(solicitudVacaciones.getId());
            Expediente expediente = solicitudVacacionesAnterior.getExpediente();
            // restar datos del expediente
            expediente.setDevengadoActualDias(
                    expediente.getDevengadoActualDias() - solicitudVacacionesAnterior.getNumeroDias());
            administracionFachada.guardarExpediente(expediente);
            // agregar datos al expediente
            expediente
                    .setDevengadoActualDias(expediente.getDevengadoActualDias() + solicitudVacaciones.getNumeroDias());
            // actualizar expediente
            administracionFachada.actualizarExpediente(expediente);
            solicitudVacacionesServicio.guardar(solicitudVacaciones);
            return true;
        }
    }

    public SolicitudVacaciones buscarSolicitudVacacionesId(long id) {
        return solicitudVacacionesServicio.buscarPorId(id);

    }

    public long contarSolicitudVacacionesExpediente(Expediente expediente) {
        return solicitudVacacionesServicio.contarSolicitudVacacionesExpediente(expediente);

    }

    public List<SolicitudVacaciones> buscarListaSolicitudVacacionesFuncionario(long id, Date fechaInicio,
            Date fechaFin) {
        // Falta Buscar expediente del funcionario para obtener id expediente
        return solicitudVacacionesServicio.listarSolicitudVacacionesExpedienteFechainicioFechafin(0, fechaInicio,
                fechaFin);
    }

    public List<SolicitudVacaciones> buscarListaSolicitudVacacionesExpediente(Expediente expediente) {
        // Falta Buscar expediente del funcionario para obtener id expediente
        return solicitudVacacionesServicio.listarSolicitudVacacionesExpediente(expediente);
    }

    public List<SolicitudVacaciones> buscarListaTodosSolicitudVacaciones() {
        return solicitudVacacionesServicio.listarTodosSolicitudVacaciones();
    }

    public SolicitudVacaciones actualizarSolicitudVacaciones(SolicitudVacaciones solicitudVacaciones) {
        return solicitudVacacionesServicio.guardar(solicitudVacaciones);
    }

    public void eliminarSolicitudVacaciones(long id) {
        SolicitudVacaciones solicitudVacaciones = this.buscarSolicitudVacacionesId(id);
        Expediente expediente = solicitudVacaciones.getExpediente();
        expediente.setDevengadoActualDias(expediente.getDevengadoActualDias() - solicitudVacaciones.getNumeroDias());
        administracionFachada.guardarExpediente(expediente);
        solicitudVacacionesServicio.eliminar(id);
    }
}
