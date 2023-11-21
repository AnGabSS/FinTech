document.addEventListener("DOMContentLoaded", function () {
    console.log("Script carregado com sucesso.");

    const taskInput = document.getElementById("taskInput");
    const addTaskBtn = document.getElementById("addTaskBtn");
    const saveTasksBtn = document.getElementById("saveTasksBtn");
    const taskList = document.getElementById("taskList");

    let taskCounter = 0;

    addTaskBtn.addEventListener("click", addTask);

    taskInput.addEventListener("keydown", function (event) {
        if (event.key === "Enter" && !saveTasksBtn.disabled) {
            addTask();
        }
    });

    function saveTasks() {
    const formData = new FormData();
    formData.append("saveTaskBtn", "true");
    formData.append("taskCounter", taskCounter); // Adiciona o parâmetro taskCounter

    for (let i = 0; i < taskCounter; i++) {
        const taskTextElements = document.getElementsByName(`taskText_${i}`);
        const textBoxElements = document.getElementsByName(`textBox_${i}`);
        const dateBoxElements = document.getElementsByName(`dateBox_${i}`);
        const statusBoxElements = document.getElementsByName(`statusBox_${i}`);
        const valueBoxElements = document.getElementsByName(`valueBox_${i}`);

        if (taskTextElements.length > 0) {
            formData.append(`taskText_${i}`, taskTextElements[0].textContent);
        }

        if (textBoxElements.length > 0) {
            formData.append(`textBox_${i}`, textBoxElements[0].value);
        }

        if (dateBoxElements.length > 0) {
            formData.append(`dateBox_${i}`, dateBoxElements[0].value);
        }

        if (statusBoxElements.length > 0) {
            formData.append(`statusBox_${i}`, statusBoxElements[0].value);
        }

        if (valueBoxElements.length > 0) {
            formData.append(`valueBox_${i}`, valueBoxElements[0].value);
        }
    }

    console.log("FormData:", formData);

    const xhr = new XMLHttpRequest();
    xhr.open("POST", "Objetivo", true);
    xhr.send(formData);

    xhr.onload = function () {
        if (xhr.status === 200) {
            console.log("Sucesso:", xhr.responseText);
            saveTasksBtn.disabled = true; // Desabilita o botão após salvar
        } else {
            console.error("Erro:", xhr.status, xhr.statusText);
        }
    };
}

    saveTasksBtn.addEventListener("click", function () {
        // Chama a função para salvar as tarefas
        saveTasks();
    });

    function addTask() {
        const taskText = taskInput.value.trim();
        if (taskText !== "") {
            const listItem = document.createElement("li");

            const taskContent = document.createElement("span");
            taskContent.textContent = taskText;
            taskContent.id = `taskText_${taskCounter}`;
            listItem.appendChild(taskContent);

            const textBox = createInput("text", "Adicionar texto", `textBox_${taskCounter}`);
            listItem.appendChild(textBox);

            const dateBox = createInput("date", "", `dateBox_${taskCounter}`);
            listItem.appendChild(dateBox);

            const statusBox = document.createElement("select");
            statusBox.className = "txBox";
            statusBox.name = `statusBox_${taskCounter}`;
            const option1 = createOption("Concluída", "true");
            const option2 = createOption("Não Concluída", "false");
            statusBox.add(option1);
            statusBox.add(option2);
            listItem.appendChild(statusBox);

            const valueBox = createInput("number", "Valor", `valueBox_${taskCounter}`);
            listItem.appendChild(valueBox);

            const editButton = createButton("Editar", `editButton_${taskCounter}`, function () {
                editTask(listItem, taskContent, textBox, dateBox, statusBox, valueBox);
            });
            listItem.appendChild(editButton);

            const deleteButton = createButton("Excluir", `deleteButton_${taskCounter}`, function () {
                deleteTask(listItem);
            });
            listItem.appendChild(deleteButton);

            taskList.appendChild(listItem);
            taskInput.value = "";

            taskCounter++;
        }
    }

    function createInput(type, placeholder, name) {
        const input = document.createElement("input");
        input.type = type;
        input.placeholder = placeholder;
        input.className = "txBox";
        input.name = name;
        return input;
    }

    function createOption(text, value) {
        const option = document.createElement("option");
        option.text = text;
        option.value = value;
        return option;
    }

    function createButton(text, id, clickHandler) {
        const button = document.createElement("button");
        button.textContent = text;
        button.className = "btnBox";
        button.id = id;
        button.addEventListener("click", clickHandler);
        return button;
    }

    function deleteTask(item) {
        taskList.removeChild(item);
    }
});