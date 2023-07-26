import React from 'react';
import axios from 'axios';
import style from './modal.module.css';

const Modal = (props) => {
    const { open, close, header } = props;
    const handleSubmit = async (event) => {
        event.preventDefault();

        try {
            const form = event.target;
            const formData = new FormData(form);
            const jsonData = {};

            // Convert FormData to JSON
            formData.forEach((value, key) => {
                jsonData[key] = value;
            });

            await axios.post('http://localhost:8080/board/addqa', jsonData, {
                headers: {
                    'Content-Type': 'application/json'
                }
            });
            // 성공적으로 전송되었을 때 처리할 코드 작성
            console.log(jsonData);
            close();
        } catch (error) {
            // 전송 실패 시 처리할 코드 작성
            console.error(error);
        }
    };


    return (
        <div className={open ? `${style.openModal} ${style.modal}` : style.modal}>
            <section>
                <header>
                    문의 하기
                    <button onClick={close}>
                        &times;
                    </button>
                </header>
                <form className={style.modal_form} onSubmit={handleSubmit}>
                    <main>
                        <label>
                            <p>아이디</p>
                            <br />
                            <input type='text' name='member_id' placeholder="작성자님의 아이디를 입력해주세요" />
                        </label>
                        <br />
                        <label>
                            <p>문의 제목</p>
                            <br />
                            <input type='text' name='qa_title' />
                        </label>
                        <br />
                        <label>
                            <p>문의 내용</p>
                            <br />
                            <textarea type='text' name='qa_content' style={{ width: '300px', height: '150px' }} />
                        </label>
                        <br />
                        <div className={style.form_radiobox}>
                            <label>
                                <input type="radio" name="qa_secret" value="yes" checked />
                                &nbsp;&nbsp;공개
                            </label>
                            <label>
                                <input type="radio" name="qa_secret" value="no" />
                                &nbsp;&nbsp;비공개
                            </label>
                            <br />
                        </div>
                    </main>
                    <footer>
                        <button type="submit">
                            문의
                        </button>
                        <button onClick={close}>
                            닫기
                        </button>
                    </footer>
                </form>
            </section>
        </div>
    );
};

export default Modal;
