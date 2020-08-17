$(function(){

	$.ajax({
		type: "GET",
		url: "/store/manager/ManagerController?op=findBooks",
		 data: "current=1",
		success: function(msg){
			var json = eval('(' + msg + ')');
			console.log(json);
			for(var j = 1;j<=json.totalPage;i++){
				
			}
			for(var i = 0;i<json.length;i++){
				var b = json[i];
				var trObj = $("#bookTable").append("<tr id='"+b.book.id+"'></tr>");

				$("#"+b.book.id).append("<td>"+b.book.id+"</td>");
				$("#"+b.book.id).append("<td>"+b.book.bookName+"</td>");
				$("#"+b.book.id).append("<td>"+b.book.sellPoint+"</td>");
				$("#"+b.book.id).append("<td>"+b.book.price+"</td>");

				$("#"+b.book.id).append("<td><img src='"+b.book.pic+"' width='60px' height='80px'/></td>");
				$("#"+b.book.id).append("<td>"+b.book.des+"</td>");
				$("#"+b.book.id).append("<td>"+b.categoryName+"</td>");
				//<button type="button" id="${category.id }"
				//class="btn btn-danger btn-sm deleteCategory">删除</button>
				$("#"+b.book.id).append("<td><button type='button' id='"+b.book.id+"' class='btn btn-danger btn-sm deleteBook'>删除</button> <button type='button' id='"+b.book.id+"' class='btn btn-primary btn-sm updateBook'>修改</button></td>");

				// servlet传递过来了一个 5 
				//<li><a href="/store/manager/ControllerManager?op=findBooks&current=1">1</a></li>
				
				
			}
		}
	});
	//如果是动态创建的标签 只能去找他的父亲（不能是动态创建的）on("click","类选择器",function)
	$("table").on("click",".deleteBook",function(){
		alert("aaa");
	})
	// 此方法为切换左边菜单的js文件
	$(".list_dt").on("click", function() {
		$('.list_dd').stop();
		$(this).siblings("dt").removeAttr("id");
		if ($(this).attr("id") == "open") {
			$(this).removeAttr("id").siblings("dd").slideToggle();
		} else {
			$(this).attr("id", "open").next().slideToggle();
		}
	});
	// 退出
	function logout() {
		window.location.href = "login.html";
	}


})
