import React, { useState } from 'react';
import Navbar from '../components/Navbar';
import Footer from '../components/Footer';
import Intro from '../components/Intro';
import Main_Slide from '../components/Main_Slide';
import Charts from '../components/Charts';
import Services from '../components/Services';
import FAQ from '../components/Main_to_FAQ';
import News from '../components/News';

import Back1 from '../assets/main_back1.jpg'
import Main_to_FAQ from "../components/Main_to_FAQ";

const Main = () => {
    const [order, setOrder] = useState(0);

    const handleNav = () => {
        setOrder((order + 1) % 3);
    };
    return (
        <div>
            <Navbar />
            <Main_Slide />
            <Charts />
            <Services />
            <Main_to_FAQ />
            <News />
            <Footer />
        </div>
    );
};

export default Main;

// <Intro img={Back1} title='편리한 전자결제 페이투스' text1='고객의 환경에 적합한 다양한 결제환경과' text2 = '안전한 결제수단을 제공합니다.' />