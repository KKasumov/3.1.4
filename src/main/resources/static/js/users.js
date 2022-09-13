let roleList = [];
getAllUsers();

function getAllUsers() {
    $.getJSON("http://localhost:8080/admin/allUsers", function (data) {
        console.log('1)  /allUsers: ', JSON.stringify(data))
        let rows = '';
        $.each(data, function (key, user) {
            rows += createRows(user);
        });
        $('#tableAllUsers').append(rows);
        $.ajax({
            url: '/admin/authorities',
            method: 'GET',
            dataType: 'json',
            success: function (roles) {
                roleList = roles;
            }
        });
    });
}

function createRows(user) {

    let user_data = '<tr id=' + user.id + '>';
    user_data += '<td>' + user.id + '</td>';
    user_data += '<td>' + user.name + '</td>';
    user_data += '<td>' + user.lastName + '</td>';

    user_data += '<td>';
    let roles = user.authorities;
    for (let role of roles) {
        user_data += role.role.replace('ROLE_', '') + ' ';
    }
    user_data += '</td>' +
        '<td>' + '<input id="btnEdit" value="Edit" type="button" ' +
        'class="btn-info btn edit-btn" data-toggle="modal" data-target="#editModal" ' +
        'data-id="' + user.id + '">' + '</td>' +

        '<td>' + '<input id="btnDelete" value="Delete" type="button" class="btn btn-danger del-btn" ' +
        'data-toggle="modal" data-target="#deleteModal" data-id=" ' + user.id + ' ">' + '</td>';
    user_data += '</tr>';

    return user_data;
}
function getUserRolesForEdit() {
    let allRoles = [];
    $.each($("select[name='editRoles'] option:selected"), function () {
        let role = {};
        role.id = $(this).attr('id');
        role.role = $(this).attr('role');
        allRoles.push(role);
        console.log("role: " + JSON.stringify(role));
    });
    return allRoles;
}
$(document).on('click', '.edit-btn', function () {
    const user_id = $(this).attr('data-id');
    console.log("editUserId: " + JSON.stringify(user_id));
    $.ajax({
        url: '/admin/' + user_id,
        method: 'GET',
        dataType: 'json',
        success: function (user) {
            $('#editId').val(user.id);
            $('#editName').val(user.name);
            $('#editLastName').val(user.lastName);

            $('#editPassword').val(user.password);
            $('#editRole').empty();
            roleList.map(role => {
                let flag = user.authorities.find(item => item.id === role.id) ? 'selected' : '';
                $('#editRole').append('<option id="' + role.id + '" ' + flag + ' role="' + role.role + '" >' +
                    role.role.replace('ROLE_', '') + '</option>')
            })
        }
    });
});
$('#editButton').on('click', (e) => {
    e.preventDefault();

    let userEditId = $('#editId').val();

    let editUser = {
        id: $("input[name='id']").val(),
        name: $("input[name='name']").val(),
        lastName: $("input[name='lastName']").val(),

        password: $("input[name='password']").val(),
        roles: getUserRolesForEdit()

    };
    $.ajax({
        url: '/admin',
        method: 'PUT',
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
        data: JSON.stringify(editUser),
        success: (data) => {
            let newRow = createRows(data);
            console.log("newRow: " + newRow)
            $('#tableAllUsers').find('#' + userEditId).replaceWith(newRow);
            $('#editModal').modal('hide');
            $('#admin-tab').tab('show');
        },
        error: () => {
            console.log("error editUser")
        }
    });
});

$(document).on('click', '.del-btn', function () {

    let user_id = $(this).attr('data-id');
    console.log("userId: " + JSON.stringify(user_id));

    $.ajax({
        url: '/admin/' + user_id,
        method: 'GET',
        dataType: 'json',
        success: function (user) {
            $('#delId').empty().val(user.id);
            $('#delName').empty().val(user.name);
            $('#delLastName').empty().val(user.lastName);

            $('#delPassword').empty().val(user.password);
            $('#delRole').empty();
            roleList.map(role => {
                let flag = user.authorities.find(item => item.id === role.id) ? 'selected' : '';
                $('#delRole').append('<option id="' + role.id + '" ' + flag + ' name="' + role.role + '" >' +
                    role.role.replace('ROLE_', '') + '</option>')
            })
        }
    });
});
$('#deleteButton').on('click', (e) => {
    e.preventDefault();
    let userId = $('#delId').val();
    $.ajax({
        url: '/admin/' + userId,
        method: 'DELETE',
        success: function () {
            $('#' + userId).remove();
            $('#deleteModal').modal('hide');
            $('#admin-tab').tab('show');
        },
        error: () => {
            console.log("error delete user")
        }
    });
});
function getUserRolesForAdd() {
    let allRoles = [];
    $.each($("select[name='addRoles'] option:selected"), function () {
        let role = {};
        role.id = $(this).attr('id');
        role.role = $(this).attr('role');
        allRoles.push(role);
        console.log("role: " + JSON.stringify(role));
    });
    return allRoles;
}
$('.newUser').on('click', () => {

    $('#name').empty().val('')
    $('#lastName').empty().val('')
    $('#age').empty().val('')
    $('#password').empty().val('')
    $('#addRole').empty().val('')
    roleList.map(role => {
        $('#addRole').append('<option id="' + role.id + '" role="' + role.role + '">' +
            role.role.replace('ROLE_', '') + '</option>')
    })
})
$("#addNewUserButton").on('click', () => {
    let newUser = {
        name: $('#name').val(),
        lastName: $('#lastName').val(),

        password: $('#password').val(),
        roles: getUserRolesForAdd()
    }
    $.ajax({
        url: 'http://localhost:8080/admin',
        method: 'POST',
        dataType: 'json',
        data: JSON.stringify(newUser),
        contentType: 'application/json; charset=utf-8',
        success: function () {
            // alert("add user in success")
            $('#tableAllUsers').empty();
            getAllUsers();
            $('#admin-tab').tab('show');
        },
        error: function () {
            alert('error addUser')
        }
    });
});