let sideBarCheck = false;
function handleGlobalClick(event) {
    const sideBar = document.querySelector('.side-bar');
    const header = document.querySelector('.header');
    const view = document.querySelector('.view');

    if (!sideBar.contains(event.target)) {
        sideBar.classList.remove('active');
        header.classList.remove('blur');
        view.classList.remove('blur');
        sideBarCheck = false;

        document.removeEventListener('click', handleGlobalClick);
    }
}

function viewSideBar() {
    const sideBar = document.querySelector('.side-bar');
    const header = document.querySelector('.header');
    const view = document.querySelector('.view');

    sideBar.classList.add('active');
    header.classList.add('blur');
    view.classList.add('blur');
    sideBarCheck = true;

    setTimeout(() => {
        document.addEventListener('click', handleGlobalClick);
    }, 0);
}