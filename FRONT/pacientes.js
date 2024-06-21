document.getElementById('listarPacientes').addEventListener('click', listarPacientes);

function listarPacientes() {
    fetch('http://localhost:8080/pacientes/listar')
        .then(response => response.json())
        .then(data => {
            const pacientesList = document.getElementById('pacientesList');
            pacientesList.innerHTML = '';
            data.forEach(paciente => {
                const div = document.createElement('div');
                div.textContent = `ID: ${paciente.id}, Nombre: ${paciente.nombre}, Apellido: ${paciente.apellido}`;
                pacientesList.appendChild(div);
            });
        })
        .catch(error => {
            console.error('Error fetching pacientes:', error);
        });
}
