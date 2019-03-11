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