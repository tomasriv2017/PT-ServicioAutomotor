function eliminar(id) {
	swal({
		  title: "¿Desea eliminar esta orden permanentemente?",
		  text: "Una vez eliminada, no será posible recuperarla",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
	})
	.then((OK) => {
		if (OK) {
			$.ajax({
				url:"/orden/delete/"+id,
				success: function(res) {
					console.log(res);
				}
			  });
			swal("La orden ha sido eliminada correctamente", {
				icon: "success",
			}).then((ok) => {
				if(ok) {
					location.href="/orden/";
				}
			});
		  } else {
			swal("La orden no ha sido eliminada");
		}
	  });
}
	
	
function quitarServicio(idOrdenDeTrabajo,idServicio) {
	swal({
		  title: "¿Desea eliminar esta orden permanentemente?",
		  text: "Una vez eliminada, no será posible recuperarla",
		  icon: "warning",
		  buttons: true,
		  dangerMode: true,
		  buttons: true,
		  dangerMode: true,
	})
	.then((OK) => {
		if (OK) {
			$.ajax({
				url:"/orden/"+idOrdenDeTrabajo+"/deleteService/"+ idServicio,
				success: function(res) {
					console.log(res);
				}
			  });			
			  then((ok) => {
				if(ok) {
					location.href="/orden/findService";
				}
			});
		  }
		}
	  );
}

	  