function eliminarCliente(id) {
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
	  
function eliminarOrden(id) {
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

function eliminarVehiculo(id) {
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
	
