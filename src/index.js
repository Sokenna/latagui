import _        from 'loadsh';
import './style.css';
import icon from  '../images/15-1Z622030A6.jpg';
import printMe from './print.js';

function component() {
    var element = document.createElement('div');

    // Lodash（目前通过一个 script 脚本引入）对于执行这一行是必需的
    element.innerHTML = _.join(['Hello', 'webpack'], ' ');
    element.classList.add('hello');
    var btn = document.createElement('button');
    btn.innerHTML = 'Click me and check the console!';
    btn.onclick = printMe;
    element.appendChild(btn);
    var myIcon = new Image();
    myIcon.src = icon;
    element.appendChild(myIcon);
    var data = { a: 1 };


    var btn2 = document.createElement('button');
    btn2.innerHTML = vm.data.a;
    element.appendChild(btn2);
    return element;
}

let element = component(); // 当 print.js 改变导致页面重新渲染时，重新获取渲染的元素
document.body.appendChild(element);
if (module.hot){
    module.hot.accept('./print.js',function () {
        console.log('Accepting the updated printMe module!');
        document.body.removeChild(element);
        element = component(); // 重新渲染页面后，component 更新 click 事件处理
        document.body.appendChild(element);
    })
}