import React, { useState } from "react";
import { Accordion } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import AccordionItem from "../cspage/accordionitem";
import Cspagebutton from "../cspage/cspagebutton";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';
import style from '../cspage/cspage.module.css';

const Cspage = () => {
  const [selectedCategory, setSelectedCategory] = useState(null);
  const [searchInput, setSearchInput] = useState('');

  const accordionItems = [
    { id: 1, category: 'Category A', accordionhead: 'Head 1', accordionbody: 'Body 1' },
    { id: 2, category: 'Category B', accordionhead: 'Head 2', accordionbody: 'Body 2' },
    { id: 3, category: 'Category A', accordionhead: 'Head 3', accordionbody: 'Body 3' },
    // Add more items as needed
  ];

  const handleCategorySelect = (category) => {
    setSelectedCategory(category);
  };

  const handleSearchInputChange = (event) => {
    setSearchInput(event.target.value);
  };

  const filteredItems = accordionItems.filter((item) => {
    if (selectedCategory && item.category !== selectedCategory) {
      return false;
    }
    if (searchInput && !item.accordionhead.toLowerCase().includes(searchInput.toLowerCase())) {
      return false;
    }
    return true;
  });

  return (
    <main>
      <nav>
        <div className={`${style.navbar_child}${style.buttonstyle}`}>
          <span>고객센터</span>
        </div>
      </nav>
      <div className={style.inputbox_wrapper}>
        <input
          id="searchInput"
          type="text"
          placeholder="자주묻는 질문 검색"
          value={searchInput}
          onChange={handleSearchInputChange}
        />
        <button>
          <i><FontAwesomeIcon icon={faSearch}/></i>
        </button>
      </div>

      <div className={style.button_box}>
        <Cspagebutton dataCategory="*" buttonName="전체" handleCategorySelect={handleCategorySelect} />
        <Cspagebutton dataCategory="shipping" buttonName="배송문의" handleCategorySelect={handleCategorySelect} />
        <Cspagebutton dataCategory="change" buttonName="취소/교환" handleCategorySelect={handleCategorySelect} />
        <Cspagebutton dataCategory="purchase" buttonName="주문/결제" handleCategorySelect={handleCategorySelect} />
        <Cspagebutton dataCategory="userService" buttonName="회원서비스" handleCategorySelect={handleCategorySelect} />
      </div>

      <div className={style.accordionBox}>
        <Accordion>
          {filteredItems.map((item, index) => (
            <AccordionItem
              key={index}
              eventKey={String(index)}
              accordionhead={item.accordionhead}
              accordionbody={item.accordionbody}
            />
          ))}
        </Accordion>
      </div>
    </main>
  );
};

export default Cspage;
