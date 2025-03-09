document.addEventListener('DOMContentLoaded', function () {
    const taskInput = document.getElementById('taskInput');
    const addTaskBtn = document.getElementById('addTaskBtn');
    const taskList = document.getElementById('taskList');
    const themeToggle = document.getElementById('themeToggle');
    const inputGroup = document.getElementById('inputGroup');
    const body = document.body;

    loadTasks();

    // Tema durumunu kontrol et (localStorage'dan)
    const isDarkMode = localStorage.getItem('darkMode') === 'true';
    if (isDarkMode) {
        body.classList.add('dark-mode');
        themeToggle.textContent = 'Light Mode';
    }

    // Tema değiştirme butonuna tıklanınca
    themeToggle.addEventListener('click', function () {
        body.classList.toggle('dark-mode');
        const isDarkMode = body.classList.contains('dark-mode');
        localStorage.setItem('darkMode', isDarkMode);
        themeToggle.textContent = isDarkMode ? 'Light Mode' : 'Dark Mode';
    });

    addTaskBtn.addEventListener('click', function () {
        const taskText = taskInput.value.trim();
        if (taskText !== "") {
            addTask(taskText);
            taskInput.value = "";
            saveTasks();
        }
    });

    inputGroup.addEventListener('click', function () {
        inputGroup.classList.add('active');
    });

    document.addEventListener('click', function (event) {
        if (!inputGroup.contains(event.target)) {
            inputGroup.classList.remove('active');
        }
    });

    function saveTasks() {
        const tasks = [];
        taskList.querySelectorAll('.list-group-item').forEach(function (task) {
            tasks.push({
                text: task.querySelector('span').innerText,
                completed: task.classList.contains('completed')
            });
        });
        localStorage.setItem('tasks', JSON.stringify(tasks));
    }

    function loadTasks() {
        const tasks = JSON.parse(localStorage.getItem('tasks')) || [];
        tasks.forEach(function (task) {
            addTask(task.text, task.completed);
        });
    }

    function addTask(taskText, completed = false) {
        const li = document.createElement('li');
        li.className = 'list-group-item';
        if (completed) {
            li.classList.add('completed');
        }

        const span = document.createElement('span');
        span.innerText = taskText;
        span.addEventListener('click', function () {
            li.classList.toggle('completed');
            saveTasks();
        });

        const deleteBtn = document.createElement('button');
        deleteBtn.innerText = "Sil";
        deleteBtn.className = 'btn btn-danger btn-sm';
        deleteBtn.addEventListener('click', function () {
            taskList.removeChild(li);
            saveTasks();
        });

        li.appendChild(span);
        li.appendChild(deleteBtn);
        taskList.appendChild(li);
    }
});