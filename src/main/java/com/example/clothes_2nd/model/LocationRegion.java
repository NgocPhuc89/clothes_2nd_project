package com.example.clothes_2nd.model;

import com.example.clothes_2nd.dto.request.LocationRegionSaveRequest;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LocationRegion extends LocationRegionSaveRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;
    private String address;
    @ManyToOne
    @JoinColumn(name = "userInfo_id")
    private UserInfo userInfo;
}
