/**
 * Validation of post, checks if title and content is filled in
 */

const postForm = document.querySelector('.post-form');
const heading = document.querySelector('#heading');
const content = document.querySelector('#content');
const postMsg = document.querySelector('.create-post .msg');

if(postForm !== null)
	postForm.addEventListener('submit', checkHeadingAndContentOnSubmit);

function checkHeadingAndContentOnSubmit(e) {
	if(heading.value.trim() === ''){
		e.preventDefault();
		postMsg.innerHTML = 'Please provide a post heading';
	}
	else if(content.value.trim() === ''){
		e.preventDefault();
		postMsg.innerHTML = 'Please provide some content to post';
	}
}