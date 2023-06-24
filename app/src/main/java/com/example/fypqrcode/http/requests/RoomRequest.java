package com.example.fypqrcode.http.requests;

public class RoomRequest {
        private Integer id;

    public RoomRequest(Integer id, String name, Integer typeID, Double space, Integer departmentID, Integer facultyID) {
        this.id = id;
        this.name = name;
        this.typeID = typeID;
        this.space = space;
        this.departmentID = departmentID;
        this.facultyID = facultyID;
    }

    private String name;

        private Integer typeID;
        private Double space;
        private Integer departmentID;
        private Integer facultyID;

        public Integer getTypeID() {
            return typeID;
        }

        public void setTypeID(Integer typeID) {
            this.typeID = typeID;
        }

        public Integer getDepartmentID() {
            return departmentID;
        }

        public void setDepartmentID(Integer departmentID) {
            this.departmentID = departmentID;
        }

        public Integer getFacultyID() {
            return facultyID;
        }

        public void setFacultyID(Integer facultyID) {
            this.facultyID = facultyID;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


        public Double getSpace() {
            return space;
        }

        public void setSpace(Double space) {
            this.space = space;
        }

}
