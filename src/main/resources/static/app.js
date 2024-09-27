let currentBookId = null; 


window.onload = function() {
    fetchBooks();
};

// Function to fetch and display books
function fetchBooks(sortBy = 'year', order = 'desc') {
    fetch(`/api/v1/book/sorted?sortBy=${sortBy}&order=${order}`)
        .then(response => response.json())
        .then(books => {
            displayBooks(books);
        })
        .catch(error => console.error('Error fetching books:', error));
}


function displayBooks(books) {
    const bookList = document.getElementById('bookList');
    bookList.innerHTML = ''; // Clear existing books

    books.forEach(book => {
        const bookItem = document.createElement('li');
        bookItem.innerHTML = `<strong>${book.name}</strong> by ${book.author} (${book.year}) - Rating: ${book.totalRating}
            <button onclick="openRatingModal(${book.id}, '${book.name}')">Rate</button>`;
        bookList.appendChild(bookItem);
    });
}


function openRatingModal(bookId, bookName) {
    currentBookId = bookId;
    document.getElementById('bookTitle').innerText = `Rate "${bookName}"`;
    document.getElementById('ratingModal').style.display = 'flex'; 
}


function closeModal() {
    document.getElementById('ratingModal').style.display = 'none'; 
}


function submitRating() {
    const rating = document.getElementById('ratingInput').value;

    if (!rating || rating < 1 || rating > 5) {
        alert('Please enter a valid rating between 1 and 5.');
        return;
    }

    fetch(`/api/v1/book/${currentBookId}/rate?score=${rating}`, {
        method: 'POST',
    })
    .then(response => {
        if (response.ok) {
            alert('Rating submitted successfully!');
            closeModal();
            fetchBooks(); // Reload the books to update ratings
        } else {
            alert('Failed to submit rating.');
        }
    })
    .catch(error => console.error('Error submitting rating:', error));
}


function applyFilters() {
    const filterName = document.getElementById('filterName').value.toLowerCase();
    const filterYear = document.getElementById('filterYear').value;
    const filterAuthor = document.getElementById('filterAuthor').value.toLowerCase();
    const filterRating = document.getElementById('filterRating').value;

    fetch(`/api/v1/book`)
        .then(response => response.json())
        .then(books => {
            let filteredBooks = books;

            
            if (filterName) {
                filteredBooks = filteredBooks.filter(book => book.name.toLowerCase().includes(filterName));
            }

           
            if (filterYear) {
                filteredBooks = filteredBooks.filter(book => book.year === Number(filterYear));
            }

            
            if (filterAuthor) {
                filteredBooks = filteredBooks.filter(book => book.author.toLowerCase().includes(filterAuthor));
            }

            
            if (filterRating) {
                filteredBooks = filteredBooks.filter(book => book.totalRating >= Number(filterRating));
            }

            // Display filtered books
            displayBooks(filteredBooks);
        })
        .catch(error => console.error('Error applying filters:', error));
}


function sortBooks() {
    const sortBy = document.getElementById('sortBy').value;
    const order = document.getElementById('order').value;

    fetchBooks(sortBy, order);
}
