-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th5 28, 2023 lúc 03:26 PM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.1.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `ta_group6_puzzle`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ta_aut_user`
--

CREATE TABLE `ta_aut_user` (
  `i_id` int(11) NOT NULL,
  `t_username` varchar(20) NOT NULL,
  `t_fullname` varchar(50) NOT NULL,
  `t_password` varchar(20) NOT NULL,
  `t_email` varchar(20) NOT NULL,
  `i_score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ta_aut_user`
--

INSERT INTO `ta_aut_user` (`i_id`, `t_username`, `t_fullname`, `t_password`, `t_email`, `i_score`) VALUES
(1, 'nlin26', 'Nhat Linh', '123456', 'linh@gmail.com', 100),
(2, 'admin', 'admin', 'password', 'admin@gmail.com', 90);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ta_que_question`
--

CREATE TABLE `ta_que_question` (
  `i_id` int(11) NOT NULL,
  `t_text` varchar(300) NOT NULL,
  `t_correct_answer` varchar(200) NOT NULL,
  `t_opt1` varchar(200) NOT NULL,
  `t_opt2` varchar(200) NOT NULL,
  `t_opt3` varchar(200) NOT NULL,
  `t_opt4` varchar(200) NOT NULL,
  `t_level` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ta_que_question`
--

INSERT INTO `ta_que_question` (`i_id`, `t_text`, `t_correct_answer`, `t_opt1`, `t_opt2`, `t_opt3`, `t_opt4`, `t_level`) VALUES
(1, '1+1=?', 'A.2', 'A.2', 'B.3', 'C.4', 'D.5', 'de');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ta_ses_question`
--

CREATE TABLE `ta_ses_question` (
  `i_id` int(11) NOT NULL,
  `i_sesstion_id` int(11) NOT NULL,
  `i_question_id` int(11) NOT NULL,
  `i_is_correct` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ta_ses_question`
--

INSERT INTO `ta_ses_question` (`i_id`, `i_sesstion_id`, `i_question_id`, `i_is_correct`) VALUES
(1, 1, 1, 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ta_ses_session`
--

CREATE TABLE `ta_ses_session` (
  `i_id` int(11) NOT NULL,
  `i_user_id` int(11) NOT NULL,
  `t_level` varchar(50) NOT NULL,
  `i_number` int(11) NOT NULL,
  `i_time` time NOT NULL,
  `i_score` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `ta_ses_session`
--

INSERT INTO `ta_ses_session` (`i_id`, `i_user_id`, `t_level`, `i_number`, `i_time`, `i_score`) VALUES
(1, 1, 'de', 20, '00:07:30', 90);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ta_aut_user`
--
ALTER TABLE `ta_aut_user`
  ADD PRIMARY KEY (`i_id`);

--
-- Chỉ mục cho bảng `ta_que_question`
--
ALTER TABLE `ta_que_question`
  ADD PRIMARY KEY (`i_id`);

--
-- Chỉ mục cho bảng `ta_ses_question`
--
ALTER TABLE `ta_ses_question`
  ADD PRIMARY KEY (`i_id`),
  ADD KEY `i_sesstion_id` (`i_sesstion_id`),
  ADD KEY `i_question_id` (`i_question_id`);

--
-- Chỉ mục cho bảng `ta_ses_session`
--
ALTER TABLE `ta_ses_session`
  ADD PRIMARY KEY (`i_id`),
  ADD KEY `i_user_id` (`i_user_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `ta_aut_user`
--
ALTER TABLE `ta_aut_user`
  MODIFY `i_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `ta_que_question`
--
ALTER TABLE `ta_que_question`
  MODIFY `i_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `ta_ses_question`
--
ALTER TABLE `ta_ses_question`
  MODIFY `i_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT cho bảng `ta_ses_session`
--
ALTER TABLE `ta_ses_session`
  MODIFY `i_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ta_ses_question`
--
ALTER TABLE `ta_ses_question`
  ADD CONSTRAINT `ta_ses_question_ibfk_1` FOREIGN KEY (`i_question_id`) REFERENCES `ta_que_question` (`i_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ta_ses_question_ibfk_2` FOREIGN KEY (`i_sesstion_id`) REFERENCES `ta_ses_session` (`i_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Các ràng buộc cho bảng `ta_ses_session`
--
ALTER TABLE `ta_ses_session`
  ADD CONSTRAINT `ta_ses_session_ibfk_1` FOREIGN KEY (`i_user_id`) REFERENCES `ta_aut_user` (`i_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
