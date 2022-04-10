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
    var appmName = document.getElementById("appmName").value;
    var appmDesc = document.getElementById("appmDesc").value;
    var appmIp = document.getElementById("appmIp").value;
    var appmPort = document.getElementById("appmPort").value;
    var appmKey = document.getElementById("appmKey").value;
    var appmAction = document.getElementById("appmAction").value;

    var newAppmInfo = {
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
            $("#modalAddAppm").modal("hide");
            $("#content-appmanager").load(location.href + " #content-appmanager");
        },
        error:function(){
            console.error('ERROR: SENDING NEW APPM INFO FAILED!');
        }
    });

}

function delAppm(){

    var appmId = document.getElementById("delAppmId").value;

    $.ajax({
        method: 'GET',
        url: '/del-appm/'+appmId,
        success:function(){
            console.info('INFO: APPM DELETED SUCCESSFULLY!');
            $("#modalDelAppm").modal("hide");
            $("#content-appmanager").load(location.href + " #content-appmanager");

        },
        error:function(){
            console.error('ERROR: FAILED TO DELETE APPM!');

        }
    });
}

function executeTestAction(appmId){

    $.ajax({
        method: 'GET',
        url: '/appminfo/'+appmId,
        success:function(data){

            var btnExecuteAction = document.querySelector('#btnExecuteAction_'+appmId);
            btnExecuteAction.setAttribute("class","spinner-grow spinner-grow-sm");
            btnExecuteAction.setAttribute("role","status");


            createTestActionUrl(data);



        },
        error:function(){
            console.error('ERROR: FAILED TO GET INFORMATION OF APPM!');

        }
    });



}


function createTestActionUrl(data){
    var id = Object.values(data)[0];
    var ip = Object.values(data)[3];
    var port = Object.values(data)[4];
    var apiKey = Object.values(data)[5];
    var actionId = Object.values(data)[6];

    var urlTestAction = "http://"+ip+":"+port+"/AppManager/json/ExecuteAction?apikey="+apiKey+"&ActionId="+actionId;

    sendUrlTestAction(urlTestAction , id);


}

function sendUrlTestAction(url , appmId){

    $.ajax({
        method: 'GET',
        url: url,
        success: function (data) {
            console.log(data);
            console.log(typeof(data));
            const obj = JSON.parse(data);
            var response = Object.values(obj)[0];
            console.log(response);

            var btnExecuteAction = document.querySelector('#btnExecuteAction_'+appmId);
            btnExecuteAction.setAttribute("class","fa fa-play");

            if(response=="4000"){

                var modalTestActionBody = document.getElementById("modalTestActionBody");
                modalTestActionBody.setAttribute("class","text-success");
                modalTestActionBody.innerHTML="Action is executed successfully!";

                $("#modalTestActionResponse").modal("show");

            }else{

                var modalTestActionBody = document.getElementById("modalTestActionBody");
                modalTestActionBody.setAttribute("class","text-danger");
                modalTestActionBody.innerHTML="Action Failed!";

                $("#modalTestActionResponse").modal("show");
            }


        },
        error: function () {
            console.error('ERROR: FAILED TO GET RESPONSE FROM APPMANAGER!');

        }
    })
}