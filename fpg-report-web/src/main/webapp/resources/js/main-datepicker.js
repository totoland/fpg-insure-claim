/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function getContextPath() {
    return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
}

var calendarTH =
        {
            datePick: function(locale, dateId) {

                this.datePick2(locale, dateId, "+533:+543");
            },
            datePick2: function(locale, dateId, yearRangeVal) {

                if ((yearRangeVal === undefined) || (yearRangeVal === '')) {
                    yearRangeVal = "+533:+543";
                }
                $(function() {
                    var d = new Date();
                    var toDay = d.getDate() + '/'
                            + (d.getMonth() + 1) + '/'
                            + (d.getFullYear() + 543);
                    $(".datepicker_th").datepicker({changeMonth: true, changeYear: true, dateFormat: 'dd/mm/yy', isBuddhist: true, defaultDate: toDay, dayNames: ['อาทิตย์', 'จันทร์', 'อังคาร', 'พุธ', 'พฤหัสบดี', 'ศุกร์', 'เสาร์'],
                        dayNamesMin: ['อา.', 'จ.', 'อ.', 'พ.', 'พฤ.', 'ศ.', 'ส.'],
                        monthNames: ['มกราคม', 'กุมภาพันธ์', 'มีนาคม', 'เมษายน', 'พฤษภาคม', 'มิถุนายน', 'กรกฎาคม', 'สิงหาคม', 'กันยายน', 'ตุลาคม', 'พฤศจิกายน', 'ธันวาคม'],
                        monthNamesShort: ['ม.ค.', 'ก.พ.', 'มี.ค.', 'เม.ย.', 'พ.ค.', 'มิ.ย.', 'ก.ค.', 'ส.ค.', 'ก.ย.', 'ต.ค.', 'พ.ย.', 'ธ.ค.']
//            ,minDate:'2013-09-10'
                        , yearRange: yearRangeVal
                                //,maxDate: maxDate
                        , showOn: 'button',
                        //you can use your local path also eg. buttonImage: 'images/x_office_calendar.png'
                        buttonImage: getContextPath() + '/resources/images/calendar.png',
                        buttonImageOnly: true,
                    });
                });
            },
            getCurrentDate: function(locale) {
                var date = new Date();
                var dateString = (date.getDate() < 10 ? ("0" + date.getDate()) : (date.getDate())) + "/" +
                        (date.getMonth() + 1 < 10 ? ("0" + (date.getMonth() + 1)) : (date.getMonth() + 1)) + "/";
                var yearStr = "";
                if (locale === 'th') {
                    if (date.getFullYear() < 2400) {
                        yearStr = date.getFullYear() + 543;
                    } else {
                        yearStr = date.getFullYear();
                    }
                    dateString = dateString + yearStr;
                } else {
                    if (date.getFullYear() < 2400) {
                        yearStr = date.getFullYear();
                    } else {
                        yearStr = date.getFullYear() - 543;
                    }
                    dateString = dateString + yearStr;
                }

                return dateString;
            }
        };






