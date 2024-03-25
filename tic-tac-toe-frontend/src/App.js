import './App.css';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import React from 'react';
import Board from './Board';
import Welcome from "./Welcome";

export default function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Welcome />} />
                <Route path="/board" element={<Board />} />
            </Routes>
        </BrowserRouter>
    );
}
