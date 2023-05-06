function eliminar(id) {
	swal({
		  title: "¿Desea eliminar este cliente permanentemente?",
		  text: "Una vez eliminado, no será posible recuperarlo",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
	})
	.then((OK) => {
		if (OK) {
			$.ajax({
				url:"/client/delete/"+id,
				success: function(res) {
					console.log(res);
				}
			  });
			swal("El cliente ha sido eliminado correctamente", {
				icon: "success",
			}).then((ok) => {
				if(ok) {
					location.href="/";
				}
			});
		  } else {
			swal("El cliente no ha sido eliminado");
		}
	  });
}
	  
