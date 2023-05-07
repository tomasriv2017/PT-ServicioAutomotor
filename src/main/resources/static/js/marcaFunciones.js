function eliminar(id) {
	swal({
		  title: "¿Desea eliminar esta marca permanentemente?",
		  text: "Una vez eliminada, no será posible recuperarla",
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
			swal("La marca ha sido eliminada correctamente", {
				icon: "success",
			}).then((ok) => {
				if(ok) {
					location.href="/";
				}
			});
		  } else {
			swal("La marca no ha sido eliminada");
		}
	  });
}
	  
