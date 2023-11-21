const taskInput = document.getElementById("taskInput");
const addTaskBtn = document.getElementById("addTaskBtn");
const taskList = document.getElementById("taskList");

taskInput.addEventListener("keydown", function(event) {
    if (event.key === "Enter") {
        addTask();
    }
});

addTaskBtn.addEventListener("click", function() {
    addTask();
});

function addTask() {
    const taskText = taskInput.value.trim();
    if (taskText !== "") {
        const listItem = document.createElement("li");

        // Texto da Tarefa
        const taskContent = document.createElement("span");
        taskContent.textContent = taskText;
        listItem.appendChild(taskContent);

        // Caixa de Texto
        const textBox = document.createElement("input");
        textBox.type = "text";
        textBox.placeholder = "Adicionar texto";
        listItem.appendChild(textBox);

        // Caixa de Datas
        const dateBox = document.createElement("input");
        dateBox.type = "date";
        listItem.appendChild(dateBox);

        // Status (Concluída ou Não Concluída)
        const statusBox = document.createElement("select");
        const option1 = document.createElement("option");
        option1.text = "Concluída";
        const option2 = document.createElement("option");
        option2.text = "Não Concluída";
        statusBox.add(option1);
        statusBox.add(option2);
        listItem.appendChild(statusBox);

        // Caixa de Valor
        const valueBox = document.createElement("input");
        valueBox.type = "text";
        valueBox.placeholder = "Valor";
        listItem.appendChild(valueBox);

        // Adiciona um botão de editar
        const editButton = document.createElement("button");
        editButton.textContent = "Editar";
        editButton.addEventListener("click", function() {
            editTask(listItem, taskContent, textBox, dateBox, statusBox, valueBox);
        });
        listItem.appendChild(editButton);

        // Adiciona um botão de excluir
        const deleteButton = document.createElement("button");
        deleteButton.textContent = "Excluir";
        deleteButton.addEventListener("click", function() {
            deleteTask(listItem);
        });
        listItem.appendChild(deleteButton);

        taskList.appendChild(listItem);
        taskInput.value = "";
    }
}

function editTask(listItem, taskContent, textBox, dateBox, statusBox, valueBox) {
    const newText = prompt("Editar tarefa:", taskContent.textContent);
    if (newText !== null) {
        taskContent.textContent = newText;
    }
}

function deleteTask(listItem) {
    listItem.remove();
}
