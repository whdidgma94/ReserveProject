let mSideBarCheck = false;
let openSidebarBtn = document.querySelector('.open-sidebar-btn');

window.onload = function (){
    openSidebarBtn = document.querySelector('.open-sidebar-btn');
    test();
}
function test(){
    if (openSidebarBtn) {
        console.log("1"+openSidebarBtn);
        openSidebarBtn.addEventListener('touchend', () => {
            console.log("2"+openSidebarBtn);
            mViewSideBar();
        });
    } else {
        console.error('.open-sidebar-btn not found');
    }

}

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

        document.removeEventListener('touchend', mHandleGlobalClick);
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
        document.addEventListener('touchend', mHandleGlobalClick);
    }, 0);
}
