const titles = {
    '/': 'Trang chủ',

    '/admin': 'Dashboard',
  };
  
  const handler = {
    get: function (target, name) {
      return Object.prototype.hasOwnProperty.call(target, name) ? target[name] + ' ' : ' ';
    },
  };
  
  const Titles = new Proxy(titles, handler);
  
  export default Titles;
  