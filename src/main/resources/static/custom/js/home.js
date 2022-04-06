$(document).ready(function(){

    $(".nav-link" ).attr("class","nav-link");
    $("#content-appmanager").hide();

    $("#btnContentDashboard").click(function () {
        $(".nav-link " ).attr("class","nav-link");
        $("#btnContentDashboard").attr("class", "nav-link open-menu active");
    })

    $("#btnContentMonitors").click(function () {
        $(".nav-link" ).attr("class","nav-link");
        $("#btnContentMonitors").attr("class", "nav-link open-menu active");
    })

    $("#btnContentChangeRequest").click(function () {
        $(".nav-link" ).attr("class","nav-link");
        $("#btnContentChangeRequest").attr("class", "nav-link open-menu active");
    })

    $("#btnContentManagement").click(function () {
        // $(".nav,.nav-treeview").attr("style","display:none");
        $(".nav-link" ).attr("class","nav-link");
        $("#btnContentManagement").attr("class", "nav-link open-menu active");
    })



    $("#btnContentAppmanager").click(function(){
        $(".nav-link" ).attr("class","nav-link");
        $("#btnContentManagement").attr("class", "nav-link open-menu active");
        $("#btnContentAppmanager").attr("class","nav-link active");
        $("#content-appmanager").show();

    })

});



var btnAddAppm = document.getElementById("btnAddAppm");
var modalAddAppm = document.getElementById("modalAddAppm");
var btnCloseModalAddAppm = document.getElementById("btnCloseModalAddAppm");

btnAddAppm.onclick = function (){
    modalAddAppm.style.display = "block";

}

window.onclick = function(event){
    if(event.target == modalAddAppm){
        modalAddAppm.style.display = "none";
    }
}

btnCloseModalAddAppm.onclick = function (){
    modalAddAppm.style.display = "none";
}

//////////////////////////////////////////////////////////////////////////
var btnDelAppm = document.getElementById("btnDelAppm");
var modalDelAppm = document.getElementById("modalDelAppm");
var btnCloseModalDelAppm = document.getElementById("btnCloseModalDelAppm");

btnDelAppm.onclick = function (){
    modalDelAppm.style.display = "block";

}

window.onclick = function(event){
    if(event.target == modalDelAppm){
        modalDelAppm.style.display = "none";
    }
}

btnCloseModalDelAppm.onclick = function (){
    modalDelAppm.style.display = "none";
}


//////////////////////////////////////////////////////////////////////////
var btnAbout = document.getElementById("btnAbout");
var modalAbout = document.getElementById("modalAbout");
var btnCloseModalAbout = document.getElementById("btnCloseModalAbout");

btnAbout.onclick = function (){
    modalAbout.style.display = "block";

}

window.onclick = function(event){
    if(event.target == modalAbout){
        modalAbout.style.display = "none";
    }
}

btnCloseModalAbout.onclick = function (){
    modalAbout.style.display = "none";
}

//////////////////////////////////////////////////////////////////////////


function addAppm(){
    var appmId = document.getElementById("appmId").value;
    var appmName = document.getElementById("appmName").value;
    var appmDesc = document.getElementById("appmDesc").value;
    var appmIp = document.getElementById("appmIp").value;
    var appmPort = document.getElementById("appmPort").value;
    var appmKey = document.getElementById("appmKey").value;
    var appmAction = document.getElementById("appmAction").value;

    var newAppmInfo = {
        "appmId" : appmId ,
        "appmName" : appmName ,
        "appmDesc" : appmDesc ,
        "appmIp" : appmIp ,
        "appmPort" : appmPort ,
        "appmKey" : appmKey ,
        "appmAction" : appmAction
    }

    PostNewAppmInfo(newAppmInfo);

}

function PostNewAppmInfo(payload){

    console.info("INFO: SENDING NEW APPM INFO!")

    $.ajax({
        method: 'POST',
        url: '/add-appm',
        data: JSON.stringify(payload),
        contentType: 'application/json; charset=utf-8',
        dataType : 'json' ,
        success:function(){
            console.info('INFO: SENDING NEW APPM INFO SUCCESSFULLY!');
            document.getElementById("modalAddAppm").style.display = "none";
        },
        error:function(){
            console.error('ERROR: SENDING NEW APPM INFO FAILED!');
        }
    });



}