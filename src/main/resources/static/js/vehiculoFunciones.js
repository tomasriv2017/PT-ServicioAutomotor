function eliminar(id) {
	swal({
		  title: "¿Está seguro de que desea eliminar este vehiculo?",
		  text: "Una vez eliminado, no será posible recuperarlo",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
	})
	.then((OK) => {
		if (OK) {
			$.ajax({
				url:"/car/delete/"+id,
				success: function(res) {
					console.log(res);
				}
			  });
			swal("El vehiculo ha sido eliminado correctamente", {
				icon: "success",
			}).then((ok) => {
				if(ok) {
					location.href="/car/";
				}
			});
		  } else {
			swal("El vehiculo no ha sido eliminado");
		}
	  });
}
	