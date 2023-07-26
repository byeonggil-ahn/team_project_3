// import React from "react";
// import style from "./modal2.module.css";
//
// const Modal2 = () => {
//     return(
//         <div className={style.delivery_container}>
//             <h2>배송 조회</h2>
//             <form action="http://info.sweettracker.co.kr/tracking/4" method="post">
//                 <div style="{display: none}" className="form-group">
//                     <label htmlFor="t_key">API key</label>
//                     <input type="text" className="form-control" id="t_key" name="t_key" placeholder="제공받은 APIKEY"
//                            value="LwAabBItViUom3nBLUcl2w"/>
//                 </div>
//                 <div style="display: none;" className="form-group">
//                     <label htmlFor="t_code">택배사 코드</label>
//                     <input type="text" className="form-control" name="t_code" id="t_code" placeholder="택배사 코드"
//                            value="04"/>
//                 </div>
//                 <div className="form-group">
//                     <label htmlFor="t_invoice">운송장 번호</label>
//                     <input type="text" className="form-control" name="t_invoice" id="t_invoice" placeholder="운송장 번호 입력"/>
//                 </div>
//                 <button type="submit" className="btn btn-default">조회하기</button>
//             </form>
//         </div>
//     )
// };
//
// export default Modal2;