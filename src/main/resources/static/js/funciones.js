$(document).ready(function(){
	
	$('#fileInput').on('change', function() {
        var fileName = $(this).val();
        $('#archivospan').text(fileName);
    });
	
});

function confirmarVenta(id, cantidad){
	$('#productoIdModal').attr('value', id)
	$("#cantidadModal").attr({ "max" : cantidad });
	$("#confirmarVentaModal").modal("show");
}

function realizarVenta(){
	var id = $('#productoIdModal').val();
	var cantidad = $("#cantidadModal").val();
	var maximo = $("#cantidadModal").attr('max');
	
	if(parseInt(cantidad) > parseInt(maximo)){
		toastr.error('Solo hay ' + maximo + ' unidades disponibles');
	}else if(cantidad < 0){
		toastr.error('Cantidad no puede ser negativa');
	}else{
		
		var formData = new FormData();
	    formData.append("id", id);
	    formData.append("cantidad", cantidad);
	    

		fetch('/TiendaTodo1/vender', {
	        method: 'post',
	        body: formData
		    }).then(function(response){       
				if (response.status == 200) {
					$("#confirmarVentaModal").modal("hide");
					toastr.info("Venta Exitosa!");
					$("#cardDiv"+id).load(location.href + " #cardDiv"+id);
				} else {
					$("#confirmarVentaModal").modal("hide");
					toastr.error("Venta Fallida!");
				}
		    }).catch(function(err) {
				$("#confirmarVentaModal").modal("hide");
				toastr.error("Error en la venta!");
		    });
	}
}
	