document.getElementById('fetchDataButton').addEventListener('click', fetchData);

function fetchData() {
    fetch('http://localhost:8080/api/data')
        .then(response => response.text())
        .then(data => {
            document.getElementById('dataContainer').textContent = data;
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
}