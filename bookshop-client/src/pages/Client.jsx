import React from 'react';
import ClientHeader from '../components/ClientHeader/ClientHeader';
import ClientFooter from '../components/ClientFooter/ClientFooter';
import ClientUseNavbar from '../components/ClientUseNavbav/ClientUseNavbav';

function Client() {
  return (
    <>
        <ClientHeader />
        <ClientUseNavbar />
      <section className="section-content mb-2">
        <div className="container">
          <header className="section-heading py-4 d-flex justify-content-between">
            <h3 className="section-title">Danh mục sản phẩm</h3>
            <a className="btn btn-secondary" href="#" role="button" style={{ height: 'fit-content' }}>Xem tất cả</a>
          </header>
          <div className="row item-grid">
            {Array.from({ length: 12 }).map((_, index) => (
              <div className="col-lg-3 col-md-6" key={index}>
                <div className="card mb-4">
                  <div className="card-body">
                    <a href="#" className="stretched-link">
                      <div className="d-flex align-items-center">
                        <img src="img/50px.png" alt="category" />
                        <span className="category-title ms-3">Sách giáo khoa</span>
                      </div>
                    </a>
                  </div>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>

      <section className="section-content mb-5">
        <div className="container">
          <header className="section-heading py-4 d-flex justify-content-between">
            <h3 className="section-title">Sản phẩm mới nhất</h3>
            <a className="btn btn-secondary" href="#" role="button" style={{ height: 'fit-content' }}>Xem tất cả</a>
          </header>
          <div className="row item-grid">
            {Array.from({ length: 12 }).map((_, index) => (
              <div className="col-lg-3 col-md-6" key={index}>
                <div className="card p-3 mb-4">
                  <a href="#" className="img-wrap text-center">
                    <img className="img-fluid" src="img/200px.png" alt="product" />
                  </a>
                  <figcaption className="info-wrap mt-2">
                    <a href="#" className="title">Tên một sản phẩm</a>
                    <div className="price mt-1 fw-bold">450.000₫</div>
                  </figcaption>
                </div>
              </div>
            ))}
          </div>
        </div>
      </section>
        <ClientFooter />
    </>
  );
}

export default Client;
