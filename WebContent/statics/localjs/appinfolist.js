$("#queryCategoryLevel1").change(function(){
	var queryCategoryLevel1 = $("#queryCategoryLevel1").val();
	if(queryCategoryLevel1 != '' && queryCategoryLevel1 != null){
		$.ajax({
			type:"GET",//请求类型
			url:"categorylevellist.json",//请求的url
			data:{pid:queryCategoryLevel1},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				$("#queryCategoryLevel2").html("");
				var options = "<option value=\"\">--请选择--</option>";
				for(var i = 0; i < data.length; i++){



					options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
				}
				$("#queryCategoryLevel2").html(options);
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				alert("加载二级分类失败！");
			}
		});
	}else{
		$("#queryCategoryLevel2").html("");
		var options = "<option value=\"\">--请选择--</option>";
		$("#queryCategoryLevel2").html(options);
	}
	$("#queryCategoryLevel3").html("");
	var options = "<option value=\"\">--请选择--</option>";
	$("#queryCategoryLevel3").html(options);
});

$("#queryCategoryLevel2").change(function(){
	var queryCategoryLevel2 = $("#queryCategoryLevel2").val();
	if(queryCategoryLevel2 != '' && queryCategoryLevel2 != null){
		$.ajax({
			type:"GET",//请求类型
			url:"categorylevellist.json",//请求的url
			data:{pid:queryCategoryLevel2},//请求参数
			dataType:"json",//ajax接口（请求url）返回的数据类型
			success:function(data){//data：返回数据（json对象）
				$("#queryCategoryLevel3").html("");
				var options = "<option value=\"\">--请选择--</option>";
				for(var i = 0; i < data.length; i++){
					//alert(data[i].id);
					//alert(data[i].categoryName);
					options += "<option value=\""+data[i].id+"\">"+data[i].categoryName+"</option>";
				}
				$("#queryCategoryLevel3").html(options);
			},
			error:function(data){//当访问时候，404，500 等非200的错误状态码
				alert("加载三级分类失败！");
			}
		});
	}else{
		$("#queryCategoryLevel3").html("");
		var options = "<option value=\"\">--请选择--</option>";
		$("#queryCategoryLevel3").html(options);
	}
});


$(".addVersion").on("click",function(){
	var obj = $(this);
	window.location.href="/AppInfoSystem/appinfoview?id="+obj.attr("appinfoid")+"&ifog=2";
});
$(".modifyVersion").on("click",function(){
	var obj = $(this);
	var status = obj.attr("status");
	var versionid = obj.attr("versionid");
	var appinfoid = obj.attr("appinfoid");
	if(status == "1" || status == "3"){//待审核、审核未通过状态下才可以进行修改操作
		if(versionid == null || versionid == ""){
			alert("该APP应用无版本信息，请先增加版本信息！");
		}else{
			window.location.href="/AppInfoSystem/appversionmodify?vid="+ versionid + "&aid="+ appinfoid;
		}
	}else{
		alert("该APP应用的状态为：【"+obj.attr("statusname")+"】,不能修改其版本信息，只可进行【新增版本】操作！");
	}
});
$(".modifyAppInfo").on("click",function(){
	var obj = $(this);
	var status = obj.attr("status");
	if(status == "1" || status == "3"){//待审核、审核未通过状态下才可以进行修改操作
		window.location.href="/AppInfoSystem/appinfomodify?id="+ obj.attr("appinfoid");
	}else{
		alert("该APP应用的状态为：【"+obj.attr("statusname")+"】,不能修改！");
	}
});


$(".saleSwichOpen").on("click",function(){
	var obj = $(this);
	var appinfoName = obj.attr("softwareName");
	var status = obj.attr("status");
	var appinfoId = obj.attr("appinfoid")
	if(status == "1"){
		alert("该商品为待审核状态不能上架");
	}else{
			if(status == "3"){
				alert("该商品审核没有通过不能上架");
		}else{
			$.ajax({
				type:"GET",
				url:"/AppInfoSystem/AppinfoUpdateStatce",
				data:{id:appinfoId,staticid:"4"},
				dataType:"json",
				success:function(data){
					if(data.infoStatic == "true"){
						alert("恭喜您以将"+appinfoName+"上架成功");
						window.location.href="/AppInfoSystem/appinfolist"
					}else if(data.infoStatic == "false"){
						alert("不好意思系统故障"+appinfoName+"上架失败");
					}
				}
			})
		}
	}
})

$(".saleSwichClose").on("click",function(){
	var obj = $(this);
	var appinfoName = obj.attr("softwareName");
	var status = obj.attr("status");
	var appinfoId = obj.attr("appinfoid")
	if(status == "4"){
		$.ajax({
			type:"GET",
			url:"/AppInfoSystem/AppinfoUpdateStatce",
			data:{id:appinfoId,staticid:"5"},
			dataType:"json",
			success:function(data){
				if(data.infoStatic == "true"){
					alert("恭喜您以将"+appinfoName+"下架架成功");
					window.location.href="/AppInfoSystem/appinfolist"
				}else if(data.infoStatic == "false"){
					alert("不好意思系统故障"+appinfoName+"下架架失败");
				}
			}
		})
	}else{
		alert("该商品不符合下架条件,必须是上架之后的商品")
	}
})

$(".viewApp").on("click",function(){
	var obj = $(this);
	var id = obj.attr("appinfoid");
	window.location.href="/AppInfoSystem/appinfoview?id="+id+"&ifog=1";
});

$(".deleteApp").on("click",function(){
	var obj = $(this);
	if(confirm("你确定要删除APP应用【"+obj.attr("appsoftwarename")+"】及其所有的版本吗？")){
		$.ajax({
			type:"GET",
			url:"/AppInfoSystem/Appinfodelapp",
			data:{id:obj.attr("appinfoid")},
			dataType:"json",
			success:function(data){
				if(data.delResult == "true"){//删除成功：移除删除行
					alert("删除成功");
					obj.parents("tr").remove();
				}else if(data.delResult == "false"){//删除失败
					alert("对不起，删除AAP应用【"+obj.attr("appsoftwarename")+"】失败");
				}else if(data.delResult == "notexist"){
					alert("对不起，APP应用【"+obj.attr("appsoftwarename")+"】不存在");
				}
			},
			error:function(data){
				alert("对不起，删除失败");
			}
		});
	}
});


