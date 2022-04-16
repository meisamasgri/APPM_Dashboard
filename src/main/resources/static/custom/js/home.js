$(document).ready(function(){

    $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

    function download_table_as_csv(table_id, separator = ',') {
        // Select rows from table_id
        var rows = document.querySelectorAll('table#' + table_id + ' tr');
        // Construct csv
        var csv = [];
        for (var i = 0; i < rows.length; i++) {
            var row = [], cols = rows[i].querySelectorAll('td, th');
            for (var j = 0; j < cols.length; j++) {
                // Clean innertext to remove multiple spaces and jumpline (break csv)
                var data = cols[j].innerText.replace(/(\r\n|\n|\r)/gm, '').replace(/(\s\s)/gm, ' ')
                // Escape double-quote with double-double-quote (see https://stackoverflow.com/questions/17808511/properly-escape-a-double-quote-in-csv)
                data = data.replace(/"/g, '""');
                // Push escaped string
                row.push('"' + data + '"');
            }
            csv.push(row.join(separator));
        }
        var csv_string = csv.join('\n');
        // Download it
        var filename = 'export_' + table_id + '_' + new Date().toLocaleDateString() + '.csv';
        var link = document.createElement('a');
        link.style.display = 'none';
        link.setAttribute('target', '_blank');
        link.setAttribute('href', 'data:text/csv;charset=utf-8,' + encodeURIComponent(csv_string));
        link.setAttribute('download', filename);
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    }

    $(".nav-link" ).attr("class","nav-link");


    $("#btnContentDashboard").click(function () {
        $(".nav-link " ).attr("class","nav-link");
        $("#btnContentDashboard").attr("class", "nav-link open-menu active");
    })

    $("#btnContentMonitors").click(function () {
        $(".nav-link" ).attr("class","nav-link");
        $("#btnContentMonitors").attr("class", "nav-link open-menu active");
        $("#content-appmanager").hide();
        $("#content-monitors").show();

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
        $("#content-monitors").hide();
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

    var btnExecuteAction = document.querySelector('#btnExecuteAction_'+appmId);
    btnExecuteAction.setAttribute("class","spinner-grow spinner-grow-sm");
    btnExecuteAction.setAttribute("role","status");

    $.ajax({
        method: 'GET',
        url: '/execute-action/'+appmId,
        success:function(data){
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
                // window.location.reload(true);
                // $("#content-appmanager").load("home.html");
                // $('#content-appmanager').html($('#content-appmanager').html())
                // $("#content-appmanager").load(window.location + " #content-appmanager");
                // $("#content-monitors").reload(location.href + " #content-monitors");


            }else{

                var modalTestActionBody = document.getElementById("modalTestActionBody");
                modalTestActionBody.setAttribute("class","text-danger");
                modalTestActionBody.innerHTML="Action Failed!";

                $("#modalTestActionResponse").modal("show");
            }



        },
        error:function(){
            console.error('ERROR: FAILED TO GET RESPONSE FROM APPM!');

        }
    });



}

function syncAppm(appmId){

    var btnSyncAppm = document.querySelector('#btnSyncAppm_'+appmId);
    btnSyncAppm.setAttribute("class","spinner-border spinner-border-sm");
    btnSyncAppm.setAttribute("role","status");

    $.ajax({
        method: 'GET',
        url: '/sync-appm/'+appmId,
        success:function(data){
            console.log(data);
            console.log(typeof(data));
            // const obj = JSON.parse(data);
            // var response = Object.values(obj)[0];
            // console.log(response);

            var btnSyncAppm = document.querySelector('#btnSyncAppm_'+appmId);
            btnSyncAppm.setAttribute("class","fa fa-refresh");

            if(data=="4000"){

                var modalTestActionBody = document.getElementById("modalTestActionBody");
                modalTestActionBody.setAttribute("class","text-success");
                modalTestActionBody.innerHTML="Sync successfully!";
                $("#modalTestActionResponse").modal("show");
                updateSyncTime(appmId);

            }else{

                var modalTestActionBody = document.getElementById("modalTestActionBody");
                modalTestActionBody.setAttribute("class","text-danger");
                modalTestActionBody.innerHTML="Sync Failed!";
                $("#modalTestActionResponse").modal("show");
            }



        },
        error:function(){
            console.error('ERROR: FAILED TO GET RESPONSE FROM APPM!');

        }
    });



}

//
// function createTestActionUrl(data){
//     var id = Object.values(data)[0];
//     var ip = Object.values(data)[3];
//     var port = Object.values(data)[4];
//     var apiKey = Object.values(data)[5];
//     var actionId = Object.values(data)[6];
//
//     var urlTestAction = "http://"+ip+":"+port+"/AppManager/json/ExecuteAction?apikey="+apiKey+"&ActionId="+actionId;
//
//     sendUrlTestAction(urlTestAction , id);
//
//
// }
//
// function sendUrlTestAction(url , appmId){
//
//     $.ajax({
//         method: 'GET',
//         url: url,
//         success: function (data) {
//             console.log(data);
//             console.log(typeof(data));
//             const obj = JSON.parse(data);
//             var response = Object.values(obj)[0];
//             console.log(response);
//
//             var btnExecuteAction = document.querySelector('#btnExecuteAction_'+appmId);
//             btnExecuteAction.setAttribute("class","fa fa-play");
//
//             if(response=="4000"){
//
//                 var modalTestActionBody = document.getElementById("modalTestActionBody");
//                 modalTestActionBody.setAttribute("class","text-success");
//                 modalTestActionBody.innerHTML="Action is executed successfully!";
//
//                 $("#modalTestActionResponse").modal("show");
//
//             }else{
//
//                 var modalTestActionBody = document.getElementById("modalTestActionBody");
//                 modalTestActionBody.setAttribute("class","text-danger");
//                 modalTestActionBody.innerHTML="Action Failed!";
//
//                 $("#modalTestActionResponse").modal("show");
//             }
//
//
//         },
//         error: function () {
//             console.error('ERROR: FAILED TO GET RESPONSE FROM APPMANAGER!');
//
//         }
//     })
// }

function updateSyncTime(appmId){
    var appmUpdateTime = document.querySelector('#appmUpdateTime_'+appmId);

    $.ajax({
        method: 'GET',
        url: '/get-appm-sync-time/' + appmId,
        success: function (data) {
            console.log(data);
            console.log(typeof (data));

            appmUpdateTime.innerHTML = data.toString();


        },
        error: function () {
            console.error('ERROR: FAILED TO GET SYNC TIME FROM APPM!');

        }
    })

}