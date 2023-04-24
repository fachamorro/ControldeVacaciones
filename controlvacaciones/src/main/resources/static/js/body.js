
setTimeout(function () {

    // Closing the alert
    $('#success-alert').alert('close');
    $('#warning-alert').alert('close');
    $('#info-alert').alert('close');
    $('#error-alert').alert('close');
}, 5000);

window.onload=()=>{
    $('.datepicker').datepicker({
    language: "es",
    autoclose: true,
    format: "yyyy",
    minViewMode: 2,
  });
  }