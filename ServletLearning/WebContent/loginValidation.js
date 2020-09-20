/**
 * Login form validation
 */
const loginForm = document.querySelector('.login');
const unameInput = document.querySelector('#uname');
const passInput = document.querySelector('#pass');
const submitBtn = document.querySelector('.login .button');
const msg = document.querySelector('.msg');

if(loginForm !== null)
	loginForm.addEventListener('submit', checkUnameAndPassOnSubmit);

function checkUnameAndPassOnSubmit(e) {
	if(unameInput.value.trim() === '' || passInput.value.trim() === ''){
		e.preventDefault();
	    msg.innerHTML = 'Please enter valid username/password';
	    //setTimeout(() => msg.remove(), 2000);
    }
}
