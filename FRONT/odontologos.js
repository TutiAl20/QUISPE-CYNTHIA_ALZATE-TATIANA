document.getElementById('listarOdontologos').addEventListener('click', listarOdontologos);

function listarOdontologos() {
    fetch('http://localhost:8080/odontologos/listar')
        .then(response => response.json())
        .then(data => {
            const odontologosList = document.getElementById('odontologosList');
            odontologosList.innerHTML = '';
            data.forEach(odontologo => {
                const div = document.createElement('div');
                div.textContent = `ID: ${odontologo.id}, Nombre: ${odontologo.nombre}, Apellido: ${odontologo.apellido}`;
                odontologosList.appendChild(div);
            });
        })
        .catch(error => {
            console.error('Error fetching odontologos:', error);
        });
}
