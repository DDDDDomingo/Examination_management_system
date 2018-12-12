$(document).ready(function(){
	var id = sessionStorage.getItem("id");
	if(id==null){
		alert("请先登录！");
		window.location.href = "login.html";
	}
	//用户头像，昵称
	var account = sessionStorage.getItem("account");

	addTopHtml(account);

});
//更改头像和名称
function addTopHtml(account) {
	$(".nav-profile-text p").detach();
	$(".nav-profile-text span").detach();
	var headHtml = "<p class=\"mb-1 text-black\">"+account+"</p>";
	$(".nav-profile-text").append(headHtml);
	var headHtml2 = "<span class=\"text-secondary text-small\">Project Manager</span>";
	$(".container-fluid .nav-profile-text").append(headHtml2);
}