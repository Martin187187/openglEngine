#version 330

in vec2 pass_textureCoords;
out vec4 out_Color;

uniform sampler2D guiTexture;

void main(void){

  out_Color = vec4(1.0, 0.0, 0.0, 1.0);

}


