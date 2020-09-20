/**
 * Validates a comment, checks if it's empty
 */

const commentForm = document.querySelector('.comment-form');
const content = document.querySelector('#comment');
const commentMsg = document.querySelector('.add-comment .msg');

if(commentForm !== null)
	commentForm.addEventListener('submit', (e) => {
		if(content.value.trim() === ''){
			e.preventDefault();
			commentMsg.innerHTML = 'Please provide a valid comment';
		}
	})