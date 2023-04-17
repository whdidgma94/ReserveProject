let mSideBarCheck = false;

function mHandleGlobalClick(event) {
    const sideBar = document.querySelector('.side-bar');
    const header = document.querySelector('.header');
    const view = document.querySelector('.view');
    const navMenu = document.querySelector('.nav-menu');

    if (!sideBar.contains(event.target)) {
        sideBar.classList.remove('active');
        header.classList.remove('blur');
        view.classList.remove('blur');
        navMenu.classList.remove('blur');
        mSideBarCheck = false;

        document.removeEventListener('click', mHandleGlobalClick);
    }
}

function mViewSideBar() {
    const sideBar = document.querySelector('.side-bar');
    const header = document.querySelector('.header');
    const view = document.querySelector('.view');
    const navMenu = document.querySelector('.nav-menu');

    sideBar.classList.add('active');
    header.classList.add('blur');
    view.classList.add('blur');
    navMenu.classList.add('blur');
    mSideBarCheck = true;

    setTimeout(() => {
        document.addEventListener('click', mHandleGlobalClick);
    }, 0);
}
function showSubMenu() {
    var subMenu = document.getElementById("sub-menu");
    if (subMenu.style.display === "block") {
        subMenu.style.display = "none";
    } else {
        subMenu.style.display = "block";
    }
}