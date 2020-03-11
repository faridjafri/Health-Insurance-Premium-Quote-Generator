$(document).ready(function() {
	$("#myForm").submit(function() {
		var name = $('#name').val();
		var gender = $("input[name=gender]:checked").val();
		var age = $('#age').val();
		var conditions = [];
        $.each($("input[name='conditions']:checked"), function(){            
        	conditions.push($(this).val());
        });
        var habits = [];
        $.each($("input[name='habits']:checked"), function(){            
        	habits.push($(this).val());
        });
		var data={
				name,
				gender,
				age,
				conditions,
				habits
		}
		data=JSON.stringify(data);
		var saveData = $.ajax({
			headers: { 
		        'Accept': 'application/json',
		        'Content-Type': 'application/json' 
		    },
			type: 'POST',
			url: "/api/quote/calculate",
			data: data,
			success: function(resultData) { 
				$("#quote").html("<h3>Health Insurance Premium for Mr."+name+": Rs. "+resultData+"</h3>")
			}
		});
		console.log(data);
		return false;
	});

	$("#clearFields").click(function() {
		$('#myForm').get(0).reset();
	});
	
});