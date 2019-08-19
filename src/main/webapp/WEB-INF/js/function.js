/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* global fetch */

function editForm(id) {
    fetch("rest/todo/" + id, {method: "get"}).then(response => response.json()).then(response => {
        var text = JSON.stringify(response);
        var obj = JSON.parse(text);
        var inner = "";
        inner += '<form>\n' +
                '<input id="id" type="hidden" value="' + obj.id + '">' +
                '<input id="todo"  value="' + obj.todo + '">\n' +
                '<input id="complete" type="hidden" value="' + obj.complete + '">\n' +
                '</form>\n' +
                '<a onclick="update(' + obj.id + ')">Save</a>';
        inner += '<a href="./">Cancel</a>';
        document.getElementById("todoname" + id).innerHTML = inner;
    });
}

function update(id) {

    let td = document.getElementById("todo").value;
    let cm = document.getElementById("complete").value;
    let todo = {
        id: parseInt(id),
        todo: td,
        complete: cm
    };
    fetch("rest/todo/" + id, {
        method: "post",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify(todo)
    }).then(response => response.json()).then(response => {
        location.reload();
    });

}

function addForm() {
            var inner = "";
            inner += '<form>\n' +
                    '<input id="todo">\n' +
                    '</form>\n' +
                    '<a onclick="add()">Add</a>\n';
            inner += '<a href="./">Back</a>';
            document.getElementById("newtodo").innerHTML = inner;
        };
