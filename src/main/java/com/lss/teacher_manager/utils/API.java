//package com.lss.teacher_manager.utils;
//
//import APIError;
//
//public class API<T> {
//
//        private T data;
//        private int code;
//        private String msg;
//
//        public static <T> API<T> ok(T data) {
//            API<T> re = new API();
//            re.setData(data);
//            re.setCode(200);
//            re.setMsg("success");
//            return re;
//        }
//
//        private void setError(int code, String message) {
//            this.code = code;
//            this.msg = message;
//        }
//
//        public static API e(APIError error) {
//            API re = new API();
//            re.setError(error.getCode(), error.getMessage());
//            return re;
//        }
//
//        public static API ok() {
//            return e(APIError.OPERATION_SUCCESS);
//        }
//
//        public API() {
//        }
//
//        public T getData() {
//            return this.data;
//        }
//
//        public int getCode() {
//            return this.code;
//        }
//
//        public String getMsg() {
//            return this.msg;
//        }
//
//        public void setData(final T data) {
//            this.data = data;
//        }
//
//        public void setCode(final int code) {
//            this.code = code;
//        }
//
//        public void setMsg(final String msg) {
//            this.msg = msg;
//        }
//
//        public boolean equals(final Object o) {
//            if (o == this) {
//                return true;
//            } else if (!(o instanceof API)) {
//                return false;
//            } else {
//                API<?> other = (API)o;
//                if (!other.canEqual(this)) {
//                    return false;
//                } else {
//                    label39: {
//                        Object this$data = this.getData();
//                        Object other$data = other.getData();
//                        if (this$data == null) {
//                            if (other$data == null) {
//                                break label39;
//                            }
//                        } else if (this$data.equals(other$data)) {
//                            break label39;
//                        }
//
//                        return false;
//                    }
//
//                    if (this.getCode() != other.getCode()) {
//                        return false;
//                    } else {
//                        Object this$msg = this.getMsg();
//                        Object other$msg = other.getMsg();
//                        if (this$msg == null) {
//                            if (other$msg != null) {
//                                return false;
//                            }
//                        } else if (!this$msg.equals(other$msg)) {
//                            return false;
//                        }
//
//                        return true;
//                    }
//                }
//            }
//        }
//
//        protected boolean canEqual(final Object other) {
//            return other instanceof API;
//        }
//
//        public String toString() {
//            return "API(data=" + this.getData() + ", code=" + this.getCode() + ", msg=" + this.getMsg() + ")";
//        }
//    }
//
//
