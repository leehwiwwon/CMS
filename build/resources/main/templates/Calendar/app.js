// app.js
document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
        plugins: [ 'interaction', 'dayGrid', 'timeGrid', 'list' ],
        header: {
            left: 'prev,next today',
            center: 'title',
            right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
        },
        editable: true,
        selectable: true,
        dateClick: function(info) {
            $('#myModal').css("display", "block");
        }
    });

    calendar.render();

    $('.close').click(function() {
        $('#myModal').css("display", "none");
    });

    $('#eventForm').submit(function(e) {
        e.preventDefault();

        var eventData = {
            title: $('#title').val(),
            description: $('#description').val(),
            start: $('#start').val(),
            end: $('#end').val()
        };

        $.ajax({
            type: "POST",
            url: "/CalendarEvent",
            contentType: "application/json",
            data: JSON.stringify(eventData),
            success: function(data) {
                calendar.addEvent(eventData);
                $('#myModal').css("display", "none");
            },
            error: function(err) {
                console.error(err);
            }
        });
    });
});
