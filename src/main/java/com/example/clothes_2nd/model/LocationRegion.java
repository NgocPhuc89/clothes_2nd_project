package com.example.clothes_2nd.model;

import com.example.clothes_2nd.dto.request.LocationRegionSaveRequest;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "location_gion")
@Accessors(chain = true)
public class LocationRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String provinceId;
    private String provinceName;
    private String districtId;
    private String districtName;
    private String wardId;
    private String wardName;
    @ManyToOne
    @JoinColumn(name = "userInfo_id")
    private UserInfo userInfo;


}
