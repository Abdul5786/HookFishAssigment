package com.Abdul.HookiFish.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="imageData")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class ImageData
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;
    private String type;

    @Lob
    @Column(name = "imageData",length = 500)
    private byte[] imageData;
}
