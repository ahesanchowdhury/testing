<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>HappyCow Chatbot</title>
    <style>
        #chatbox {
          width: 300px;
          height: 400px;
          border: 1px solid #ccc;
          padding: 10px;
          overflow-y: auto;
          position: fixed;
          bottom: 20px;
          right: 20px;
          background: #fff;
          border-radius: 10px;
          font-family: Arial, sans-serif;
        }
        .msg { margin: 5px 0; }
        .user { color: blue; }
        .bot { color: green; }
        #inputArea {
          display: flex;
          margin-top: 10px;
        }
        #userInput { flex: 1; padding: 5px; }
        button { padding: 5px; }
    </style>
</head>
<body>
    <h2>Welcome to HappyCow 🐄</h2>
    <p>Ask me anything about our services!</p>

    <!-- Chatbot widget -->
    <div id="chatbox">
        <div id="messages"></div>
        <div id="inputArea">
            <input type="text" id="userInput" placeholder="Ask me something..." />
            <button onclick="sendMessage()">Send</button>
        </div>
    </div>

    <script>
      function sendMessage() {
        const userMsg = document.getElementById("userInput").value.trim();
        if (!userMsg) return;

        const messagesDiv = document.getElementById("messages");
        messagesDiv.innerHTML += `<div class="msg user"><b>You:</b> ${userMsg}</div>`;
        document.getElementById("userInput").value = "";

        fetch("<%= request.getContextPath() %>/chatbot", {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: "message=" + encodeURIComponent(userMsg)
        })
        .then(res => res.text())
        .then(reply => {
          messagesDiv.innerHTML += `<div class="msg bot"><b>Bot:</b> ${reply}</div>`;
          messagesDiv.scrollTop = messagesDiv.scrollHeight;
        })
        .catch(err => {
          messagesDiv.innerHTML += `<div class="msg bot"><b>Bot:</b> Sorry, error occurred.</div>`;
        });
      }
    </script>
</body>
</html>
