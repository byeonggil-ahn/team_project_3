import { React, useState } from 'react';
import Topimg from '../topimg/topimg';
import style from '../itemmain/itemmain.module.css';
import ItemSlide from '../items/productSlide'
import Items from '../items/productsItems'
import useCategory from "../../hooks/category_hooks";


const Itemmain = () => {
    const {category, loading, error} = useCategory();
    const [selectedCategory, setSelectedCategory] = useState();
    const [categoryNo,setCategoryNo]= useState(0)

    const clickbutton = (tagNo) => {
        setCategoryNo(tagNo)
    }
    return (
        <>
            <Topimg />
            <div className={style.middle_big_container}>
                <div className={style.middle_big_container_textbox}>
                    <div className={`${style.middle_left_box_child1} ${style.middle_slide_up1}`}>BEST ITEMS</div>
                    <div className={`${style.middle_left_box_child2} ${style.middle_slide_up2}`}>"sale for this season"</div>
                </div>
                <div className={style.slide_box_container}>
                    <ItemSlide />
                </div>
            </div>
            <div className={style.slide_horizon}></div>

            <div className={style.bottom_big_container}>
                <div className={style.menubox_container}>
                    <div className={style.menu__btn__container}>
                        {category.map((list) => (
                            <button onClick={() => clickbutton(list.tag_no)}  id={list.tag_no}>
                                <span className={style.menu_button_span}>{list.name}</span>
                            </button>
                        ))}
                    </div>
                </div>
            </div>
            <div className={style.menubox_horizon}></div>
            <Items categoryNo={categoryNo}/>

        </>
    );
};

export default Itemmain;