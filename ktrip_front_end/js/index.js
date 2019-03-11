// Bootstrap Material DatePicker
$(function () {
    $('#start-date').bootstrapMaterialDatePicker({
        format: 'YYYY/MM/DD',
        time: false,
        weekend: 0,
        setDate: moment()
    })
});

$(function () {
    $('#end-date').bootstrapMaterialDatePicker({
        format: 'YYYY/MM/DD',
        time: false,
        weekend: 0,
        setDate: moment()
    })
});

// Modal
$(function(){
    $("#popbutton").click(function(){
        $("#modal-link").click(function(){
            $("#my-modal").modal();
        });
    })
})

// Popup 
function myFunction() {
    var popup = document.getElementById("myPopup");
    popup.classList.toggle("show");
};