document.addEventListener('DOMContentLoaded', function () {
    const taskInput = document.getElementById('taskInput');
    const addTaskBtn = document.getElementById('addTaskBtn');
    const taskList = document.getElementById('taskList');

    loadTasks();

    addTaskBtn.addEventListener('click', function () {
        const taskText = taskInput.value.trim();
        if (taskText !== "") {
            addTask(taskText);
            taskInput.value = "";
            saveTasks();
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