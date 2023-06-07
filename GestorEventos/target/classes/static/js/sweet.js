function confirmarBorrado(event) {
    event.preventDefault();
    Swal.fire({
        title: '¿Estás seguro?',
        text: 'Esta acción eliminará el evento',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Sí, eliminar',
        cancelButtonText: 'Cancelar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Aquí puedes realizar la acción de eliminación del evento
            // Acceder a las variables idEvento e idUsuario
            var idEvento = window.idEvento;
            var idUsuario = window.idUsuario;
            // Obtener la ruta definida por defecto en el enlace
            var ruta = "/user/usuarios/" + idUsuario + "/eventos/delete/" + idEvento;

            // Redirigir al usuario a la ruta obtenida
            window.location.href = ruta;
        }
    });

}