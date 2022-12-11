
function functEliminar(id){

    swal({
        title: "Atención",
        text: "¿Realmente quiere eliminar el registro?",
        icon: "warning",
        buttons: true,
        dangerMode: true,
    })
    .then((willDelete) => {
        if (willDelete) {
            $( document ).ready(function() {

                $.ajax({
                    url: '/admin/dash-planes/eliminar/'+id,
                    success: function(respuesta) {
                        console.log(respuesta.name);
                    },
                    error: function() {
                        console.error("No es posible completar la operación");
                    }
                });

            });
            swal('Información','Registro eliminado', {
                icon: "success",
            });
        }
    });
}