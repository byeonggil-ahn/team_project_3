import {useEffect, useState} from "react";
import axios from "axios";
import Modal from "../modal/modal";
import ReactPaginate from "react-paginate";
import style from "./iteminfo.module.css"

const Board = ({member_id}) => {

    const [modalOpen, setModalOpen] = useState(false);
    const [boardList, setBoardList] = useState([]);
    const [currentPage, setCurrentPage] = useState(0); // 현재 페이지
    const itemsPerPage = 5; // 페이지당 보여줄 항목 수

    useEffect(() => {
        axios
            .get('http://localhost:8080/board/all')
            .then(response => {
                if (member_id) {
                    const filteredList = response.data.filter(item => item.member_id === member_id);
                    setBoardList(filteredList);
                } else {
                    setBoardList(response.data);
                }
            })
            .catch(error => {
                console.error("게시글을 불러오는데 실패했습니다.")
            });
    }, [member_id]);

    const openModal = () => {
        setModalOpen(true);
    };

    const closeModal = () => {
        setModalOpen(false);
    };

    const handlePageClick = ({ selected }) => {
        setCurrentPage(selected);
    };



    const offset = currentPage * itemsPerPage;
    const currentData = boardList.slice(offset, offset + itemsPerPage);
    const pageCount = Math.ceil(boardList.length / itemsPerPage)

    return (
        <>
            <div className={style.qnacontainer1} id="qna">
                <div className={style.qnachild_wrapper}>
                    <div className={style.qnachild1}>
                        <b>상품 Q&A</b>
                        <p>
                            -상품에 대해 궁금한 점을 남겨주세요.<br />
                            -문의 답변은 마이페이지상품Q&A에서 확인하실 수 있습니다.<br />
                        </p>
                    </div>
                    <button onClick={openModal} className={style.qnachild2}>문의하기</button>
                    <Modal open={modalOpen} close={closeModal} header="Modal heading" />
                </div>
                <table className={style.qnatable}>
                    <thead>
                    <tr>
                        <th></th>
                        <th colSpan="3">문의/답변</th>
                        <th></th>
                        <th>작성자</th>
                        <th>작성일</th>
                    </tr>
                    </thead>
                    <tbody>
                    {currentData.map((item, index) => (
                        <tr key={index}>
                            <td></td>
                            <td colSpan="3">{item.qa_title}</td>
                            <td></td>
                            <td>{item.member_id}</td>
                            <td>{item.qa_regdate}</td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                {/*    pagination 만들어주는 라이브러리*/}
                <ReactPaginate
                    previousLabel={'이전'}
                    nextLabel={'다음'}
                    breakLabel={'...'}
                    breakClassName={style.breakme}
                    pageCount={pageCount}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={5}
                    onPageChange={handlePageClick}
                    containerClassName={style.pagenationcontainer}
                    subContainerClassName={style.pagenation}
                    previousClassName={style.beforenext}
                    nextClassName={style.beforenext}
                    activeClassName={style.active}
                />
            </div>
        </>
    )
}

export default Board;