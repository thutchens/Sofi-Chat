$ ->
  $.get "/chats", (data) ->
    $.each data, (index, item) ->
      $("#message").append "  -" + item.msgFrom + ": " + item.message + "<br><br>"